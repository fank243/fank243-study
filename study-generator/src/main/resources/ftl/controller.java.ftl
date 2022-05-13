package ${package.Controller};


import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import com.fank243.study.api.${package.ModuleName}.I${entity}Api;
import com.fank243.study.api.${package.ModuleName}.dto.${entity}DTO;
import com.fank243.study.api.${package.ModuleName}.vo.${entity}VO;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${table.entityName}Entity;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.api.constants.ValidatorGroup;
import java.util.Arrays;
import java.util.List;
import cn.hutool.core.bean.BeanUtil;
import com.fank243.study.core.exception.BizException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!} 控制器
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} implements I${entity}Api{
<#else>
public class ${table.controllerName} implements ${entity}Api {
</#if>

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @Override
    public ResultInfo<${entity}VO> getById(String id){
        ${entity}Entity ${entity?uncap_first} = ${table.serviceName?uncap_first}.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(${entity?uncap_first},${entity}VO.class));
    }

    @Override
    public ResultInfo<PageBean<${entity}VO>> page(@RequestBody ${entity}DTO ${entity?uncap_first}){
        return ResultInfo.ok(${table.serviceName?uncap_first}.page(${entity?uncap_first}));
    }

    @Override
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) ${entity}DTO ${entity?uncap_first}) throws BizException{
        boolean isOk = ${table.serviceName?uncap_first}.add(${entity?uncap_first});
        return isOk ? ResultInfo.ok().message("添加成功"):ResultInfo.fail("添加失败");
    }

    @Override
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) ${entity}DTO ${entity?uncap_first}) throws BizException{
        boolean isOk = ${table.serviceName?uncap_first}.add(${entity?uncap_first});
        return isOk ? ResultInfo.ok().message("修改成功"):ResultInfo.fail("修改失败");
    }

    @Override
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException{
        boolean isOk = ${table.serviceName?uncap_first}.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功"):ResultInfo.fail("删除失败");
    }
}
