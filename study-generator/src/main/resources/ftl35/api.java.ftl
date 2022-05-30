package ${package.Controller};


import com.fank243.study.api.constants.ApiConstants;
import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.${package.ModuleName}.dto.${entity}DTO;
import com.fank243.study.api.${package.ModuleName}.vo.${entity}VO;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.common.model.PageBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import com.fank243.study.core.exception.BizException;

/**
 * ${table.comment!} Api
 *
 * @author ${author}
 * @since ${date}
 */
public interface I${entity}Api {

     /**
     * ${table.comment!}_根据ID查找
     *
     * @param id 主键ID
     * @return 用户实体
     */
     @GetMapping(ApiConstants. + "/get/{id}")
     ResultInfo<${entity}VO> getById(@PathVariable("id") String id) ;

      /**
      * ${table.comment!}_分页
      *
      * @param ${entity?uncap_first} 查询条件
      * @return 列表
      */
      @PostMapping(ApiConstants. + "/page")
      ResultInfo<PageBean<${entity}VO>> page(@RequestBody ${entity}DTO ${entity?uncap_first});

      /**
      * ${table.comment!}_新增
      *
      * @param ${entity?uncap_first} 请求参数
      * @return 操作结果
      * @throws BizException 业务异常，见{@link BizException}
      */
      @PostMapping(ApiConstants. + "/add")
      ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) ${entity}DTO ${entity?uncap_first}) throws BizException;

      /**
      * ${table.comment!}_修改
      *
      * @param ${entity?uncap_first} 请求参数
      * @return 操作结果
      * @throws BizException 业务异常，见{@link BizException}
      */
      @PostMapping(ApiConstants. + "/modify")
      ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) ${entity}DTO ${entity?uncap_first}) throws BizException;

      /**
      * ${table.comment!}_删除
      *
      * @param ids 主键ID集合
      * @return 操作结果
      * @throws BizException 业务异常，见{@link BizException}
      */
      @DeleteMapping(ApiConstants. + "/delete")
      ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;
}
