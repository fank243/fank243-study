package ${package.Controller};


import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import com.fank243.study.${package.ModuleName}.domain.dto.${entity}DTO;
import com.fank243.study.${package.ModuleName}.domain.vo.${entity}VO;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${table.entityName}Entity;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.common.core.domain.model.PageBean;
import com.fank243.study.api.constants.ValidatorGroup;
import java.util.Arrays;
import java.util.List;
import cn.hutool.core.bean.BeanUtil;
import BizException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!} 控制器
 *
 * @author ${author}
 * @since ${date}
 */
@RequestMapping()
@RestController
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * ${table.comment!} > 根据ID获取
    *
    * @param id 主键ID
    * @return 响应实体
    */
    @GetMapping("/getById/{id}")
    public ResultInfo<${entity}VO> getById(@PathVariable String id){
        ${entity}Entity ${entity?uncap_first} = ${table.serviceName?uncap_first}.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(${entity?uncap_first},${entity}VO.class));
    }

    /**
    * ${table.comment!} > 分页
    *
    * @param ${entity?uncap_first} 查询条件
    * @return 列表
    */
    @PostMapping("/page")
    public ResultInfo<PageBean<${entity}VO>> page(@RequestBody ${entity}DTO ${entity?uncap_first}){
        return ResultInfo.ok(${table.serviceName?uncap_first}.page(${entity?uncap_first}));
    }

    /**
    * ${table.comment!} > 新增
    *
    * @param ${entity?uncap_first} 请求参数
    * @return 操作结果
    * @throws BizException 业务异常，见{@link BizException}
    */
    @PostMapping("/add")
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) ${entity}DTO ${entity?uncap_first}) throws BizException{
        boolean isOk = ${table.serviceName?uncap_first}.add(${entity?uncap_first});
        return isOk ? ResultInfo.ok().message("添加成功"):ResultInfo.fail("添加失败");
    }

    /**
    * ${table.comment!} > 修改
    *
    * @param ${entity?uncap_first} 请求参数
    * @return 操作结果
    * @throws BizException 业务异常，见{@link BizException}
    */
    @PostMapping("/modify")
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) ${entity}DTO ${entity?uncap_first}) throws BizException{
        boolean isOk = ${table.serviceName?uncap_first}.add(${entity?uncap_first});
        return isOk ? ResultInfo.ok().message("修改成功"):ResultInfo.fail("修改失败");
    }

    /**
    * ${table.comment!} > 删除
    *
    * @param ids 主键ID集合
    * @return 操作结果
    * @throws BizException 业务异常，见{@link BizException}
    */
    @DeleteMapping("/delete")
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException{
        boolean isOk = ${table.serviceName?uncap_first}.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功"):ResultInfo.fail("删除失败");
    }
}