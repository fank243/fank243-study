package #(packageConfig.mapperPackage);

#if(mapperConfig.isMapperAnnotation())
import org.apache.ibatis.annotations.Mapper;
#end
import org.apache.ibatis.annotations.Param;
import java.util.List;
import #(primaryPropertyType);
import #(mapperConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(dtoName);

/**
* #(table.getComment()) 映射层
*
* @author #(javadocConfig.getAuthor())
* @since #(javadocConfig.getSince())
*/
#if(mapperConfig.isMapperAnnotation())
@Mapper
#end
public interface #(table.buildMapperClassName()) extends #(mapperConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

    /**
    * 根据主键ID查询
    *
    * @param #(primaryPropertyName) 主键
    * @return #(table.getComment())
    */
    #(dtoName) findBy#(primaryPropertyNameUpper)(#(propertySimpleType) #(primaryPropertyName));

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return #(table.getComment())
    */
    #(dtoName) findByCondition(@Param("params") #(dtoName) params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return #(table.getComment())
    */
    List<#(dtoName)> findListByCondition(@Param("params") #(dtoName) params);
}
