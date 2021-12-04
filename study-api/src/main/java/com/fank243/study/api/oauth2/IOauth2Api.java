//package com.fank243.study.api.oauth2;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.fank243.study.api.constants.ApiConstants;
//import com.fank243.study.api.oauth2.dto.LoginUserDTO;
//import com.fank243.study.api.oauth2.vo.AccessTokenVO;
//import com.fank243.study.common.utils.ResultInfo;
//
///**
// * Oauth2 授权
// *
// * @author FanWeiJie
// * @since 2021-09-03
// */
//public interface IOauth2Api {
//
//    /**
//     * 获取令牌
//     *
//     * @param loginUserDTO 请求参数
//     * @return 用户实体
//     */
//    @GetMapping(ApiConstants.BASE_URI_OAUTH2 + "/token")
//    ResultInfo<AccessTokenVO> token( LoginUserDTO loginUserDTO);
//
//    /**
//     * 刷新令牌
//     *
//     * @param loginUserDTO 请求参数
//     * @return 用户实体
//     */
//    @GetMapping(ApiConstants.BASE_URI_OAUTH2 + "/refresh")
//    ResultInfo<AccessTokenVO> refresh(@RequestBody LoginUserDTO loginUserDTO);
//
//}
