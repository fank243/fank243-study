/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.support.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.core.domain.dto.OperLogDTO;
import com.github.fank243.kong.core.domain.model.PageBean;
import com.github.fank243.kong.core.exception.BizException;
import com.github.fank243.kong.core.model.redis.RedisService;
import com.github.fank243.kong.sms.properties.KongSmsConfigProperties;
import com.github.fank243.kong.sms.strategy.SmsStrategy;
import com.github.fank243.kong.sms.strategy.impl.AliyunSmsStrategy;
import com.github.fank243.kong.support.domain.SmsEntity;
import com.github.fank243.kong.support.domain.dto.SmsCodeDTO;
import com.github.fank243.kong.support.domain.dto.SmsContentDTO;
import com.github.fank243.kong.support.domain.dto.SmsDTO;
import com.github.fank243.kong.support.domain.vo.OperLogVO;
import com.github.fank243.kong.support.domain.vo.SmsCodeVO;
import com.github.fank243.kong.support.domain.vo.SmsSendVO;
import com.github.fank243.kong.support.repository.ISmsRepository;
import com.github.fank243.kong.tool.result.ResultInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;

/**
 * 短信表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Service
public class SmsService {

    @Resource
    private ISmsRepository smsRepository;
    @Resource
    private RedisService redisService;
    @Resource
    private KongSmsConfigProperties kongSmsConfigProperties;

    /**
     * 短信表_分页
     *
     * @param reqRespLog 查询条件
     * @return 列表
     */
    public PageBean<OperLogVO> page(OperLogDTO reqRespLog) {
        // // TODO FanWeiJie 添加查询条件
        // QueryWrapper wrapper = new QueryWrapper();
        // Page<SmsEntity> smsEntityPage =
        // smsMapper.paginate(new Page<>(reqRespLog.getCurrPage(), reqRespLog.getPageSize()), wrapper);
        // return BeanUtils.convert(smsEntityPage, OperLogVO.class);
        return null;
    }

    /**
     * 短信表_新增
     *
     * @param smsDTO 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SmsDTO smsDTO) throws BizException {
        SmsEntity entity = BeanUtil.toBean(smsDTO, SmsEntity.class);
        smsRepository.save(entity);
        return true;
    }

    private ResultInfo<?> validateMobile(String mobile) {
        Object obj = redisService.getObj(CacheConstants.SMS_MOBILE_LOCK + mobile);
        if (obj != null) {
            return ResultInfo.err500("短信发送过于频繁");
        }
        return ResultInfo.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> send(SmsContentDTO smsContentDTO) throws BizException {
        ResultInfo<?> result = validateMobile(smsContentDTO.getMobile());
        if (!result.isSuccess()) {
            return result;
        }

        SmsSendVO smsSendVO = BeanUtil.copyProperties(smsContentDTO, SmsSendVO.class);
        smsSendVO.setMsgId(IdUtil.simpleUUID());

        SmsDTO smsDTO = BeanUtil.copyProperties(smsContentDTO, SmsDTO.class);
        smsDTO.setMsgId(smsSendVO.getMsgId());
        smsDTO.setContent("短信内容");

        redisService.setObj(CacheConstants.SMS_MOBILE_LOCK, smsContentDTO.getMobile());

        // 添加短信发送记录
        boolean isOk = add(smsDTO);

        return isOk ? ResultInfo.ok(smsSendVO).message("短信发送成功") : ResultInfo.err500("短信发送失败");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> sendSmsCode(SmsCodeDTO smsCodeDTO) throws BizException {
        ResultInfo<?> resultInfo = validateMobile(smsCodeDTO.getMobile());
        if (!resultInfo.isSuccess()) {
            return resultInfo;
        }

        String smsCode = RandomUtil.randomNumbers(6);

        SmsStrategy smsStrategy = new AliyunSmsStrategy(kongSmsConfigProperties.getAliyun());
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(smsCodeDTO.getMobile());
        request.setSignName("阿里云");
        request.setTemplateCode("A123456");
        JSONObject templateParam = new JSONObject();
        templateParam.set("name", "账号");
        templateParam.set("amount", "100");
        request.setTemplateParam(templateParam.toString());
        ResultInfo<?> result = smsStrategy.sendSms(JSONUtil.toJsonStr(request));
        System.out.println(result);
        SendSmsResponse response = (SendSmsResponse)result.getPayload();

        String msgId = IdUtil.simpleUUID();
        smsCodeDTO.setMsgId(msgId);
        smsCodeDTO.setSmsCode(smsCode);
        boolean isOk = redisService.setObj(CacheConstants.SMS_CODE_KEY + msgId, smsCodeDTO, TimeConstants.MINUTE_5);
        if (!isOk) {
            return ResultInfo.err500("短信验证码发送失败");
        }
        redisService.setObj(CacheConstants.SMS_MOBILE_LOCK + smsCodeDTO.getMobile(), "1", TimeConstants.MINUTE_1);

        SmsDTO smsDTO = BeanUtil.copyProperties(smsCodeDTO, SmsDTO.class);
        smsDTO.setContent("您本次的验证码是" + smsCode + "，5分钟内有效，为保障安全，请勿泄露。");

        // 添加短信发送记录
        isOk = add(smsDTO);

        SmsCodeVO smsCodeVO = BeanUtil.copyProperties(smsCodeDTO, SmsCodeVO.class);
        return isOk ? ResultInfo.ok(smsCodeVO).message("短信验证码发送成功") : ResultInfo.err500("短信验证码发送失败");
    }

    public ResultInfo<?> validateSmsCode(SmsCodeDTO smsCodeDTO) {
        String key = CacheConstants.SMS_CODE_KEY + smsCodeDTO.getMsgId();
        Object obj = redisService.getObj(key);
        if (obj == null) {
            return ResultInfo.err500("短信验证码已过期失效，请重新获取");
        }
        SmsCodeDTO redisSmsCodeDTO = (SmsCodeDTO)obj;
        if (!redisSmsCodeDTO.getMobile().equalsIgnoreCase(smsCodeDTO.getMobile())) {
            return ResultInfo.err500("短信验证码不正确");
        }
        if (ObjUtil.equals(redisSmsCodeDTO.getSmsCodeType(), smsCodeDTO.getSmsCodeType())) {
            return ResultInfo.err500("短信验证码不正确");
        }
        if (!redisSmsCodeDTO.getSmsCode().equalsIgnoreCase(smsCodeDTO.getSmsCode())) {
            return ResultInfo.err500("短信验证码不正确");
        }

        // 清空缓存
        redisService.delete(key);

        return ResultInfo.ok().message("短信验证码校验通过");
    }
}
