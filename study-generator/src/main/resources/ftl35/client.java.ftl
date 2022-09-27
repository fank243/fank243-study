package ${package.Controller};

import org.springframework.cloud.openfeign.FeignClient;

/**
 * ${table.comment!} 客户端
 *
 * @author ${author}
 * @since ${date}
 */
@FeignClient(value = "server-${package.ModuleName}", path = )
public interface I${entity}Service {

}

