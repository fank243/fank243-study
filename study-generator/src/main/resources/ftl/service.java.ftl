package ${package.Service};

import ${package.Entity}.${table.entityName}Entity;
import org.springframework.stereotype.Service;
import com.fank243.study.api.${package.ModuleName}.dto.${entity}DTO;
import com.fank243.study.api.${package.ModuleName}.vo.${entity}VO;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.model.PageBean;
import ${package.Mapper}.${table.mapperName};
import com.fank243.study.ds.utils.BeanUtils;
import com.fank243.study.core.exception.BizException;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fank243.study.core.model.PageBean;
import javax.annotation.Resource;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceName} extends ServiceImpl<${table.mapperName}, ${entity}Entity> {

    @Resource
    private ${table.mapperName} ${table.mapperName?substring(1)?uncap_first};

    /**
    * ${table.comment!}_分页
    *
    * @param ${entity?uncap_first} 查询条件
    * @return 列表
    */
    public PageBean<${entity}VO> page(${entity}DTO ${entity?uncap_first}){
        // TODO ${author} 添加查询条件
        QueryWrapper<${entity}Entity> wrapper = new QueryWrapper<>();
        IPage<${entity}Entity> page =${table.mapperName?substring(1)?uncap_first}.selectPage(new Page<>(${entity?uncap_first}.getCurrPage(), ${entity?uncap_first}.getPageSize()), wrapper);
        return BeanUtils.convert(page,${entity}VO.class);
    }

    /**
    * ${table.comment!}_新增
    *
    * @param ${entity?uncap_first} 请求参数
    * @return 操作结果
    */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(${entity}DTO ${entity?uncap_first}) throws BizException{
        // TODO ${author} 业务逻辑
        ${entity}Entity ${entity?uncap_first}Entity = BeanUtil.toBean(${entity?uncap_first},${entity}Entity.class);
        return save(${entity?uncap_first}Entity);
    }

    /**
    * ${table.comment!}_修改
    *
    * @param ${entity?uncap_first} 请求参数
    * @return 操作结果
    */
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(${entity}DTO ${entity?uncap_first}) throws BizException{
        // TODO ${author} 业务逻辑
        ${entity}Entity ${entity?uncap_first}Entity = BeanUtil.toBean(${entity?uncap_first},${entity}Entity.class);
        return ${table.mapperName?substring(1)?uncap_first}.updateById(${entity?uncap_first}Entity) > 0;
    }
}
