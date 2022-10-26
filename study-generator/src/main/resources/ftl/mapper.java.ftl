package ${package.Mapper};

import ${package.Entity}.domain.${entity}Entity;
import ${superMapperClassPackage};
import org.springframework.stereotype.Repository;

/**
* ${table.comment!} 数据访问层
*
* @author ${author}
* @since ${date}
*/
@Repository
public interface I${table.mapperName} extends ${superMapperClass}
<${entity}Entity> {

	}