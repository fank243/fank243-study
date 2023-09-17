package #(packageConfig.mapperPackage);

#if(mapperConfig.isMapperAnnotation())
import org.apache.ibatis.annotations.Mapper;
#end
import org.apache.ibatis.annotations.Param;
import java.util.List;
import #(primaryPropertyType);
import #(mapperConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());

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
#(table.buildEntityClassName()) findBy#(primaryPropertyNameUpper)(#(propertySimpleType) #(primaryPropertyName));

/**
* 多条件查询
*
* @param #(entityNameLower) 条件参数
* @return #(table.getComment())
*/
#(table.buildEntityClassName()) findByCondition(@Param("#(entityNameLower)") #(table.buildEntityClassName()) #(entityNameLower));

/**
* 多条件查询
*
* @param #(entityNameLower) 条件参数
* @return #(table.getComment())
*/
List<#(table.buildEntityClassName())> findListByCondition(@Param("#(entityNameLower)") #(table.buildEntityClassName()) #(entityNameLower));
}
