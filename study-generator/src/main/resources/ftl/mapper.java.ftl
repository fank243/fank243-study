package ${package.Mapper};

import ${package.Entity}.${entity}Entity;
import ${superMapperClassPackage};
import org.springframework.stereotype.Repository;

/**
 * <p>
 * ${table.comment!} 数据访问层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
public interface ${table.mapperName} extends ${superMapperClass}<${entity}Entity> {

}
