package ${package.Controller};

import com.fank243.study.api.${package.ModuleName}.I${entity}Api;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ${table.comment!} 客户端
 *
 * @author ${author}
 * @since ${date}
 */
@FeignClient(value = "server-${package.ModuleName}")
public interface I${entity}Service extends I${entity}Api {

}
