#set(withLombok = entityConfig.isWithLombok())
#set(withSwagger = entityConfig.isWithSwagger())
#set(swaggerVersion = entityConfig.getSwaggerVersion())
#set(withActiveRecord = entityConfig.isWithActiveRecord())
#set(entityClassName = table.buildEntityClassName())
package #(packageConfig.entityPackage);

import com.github.fank243.study.core.base.BaseDTO;
#for(importClass : table.buildImports())
import #(importClass);
#end
#if(withActiveRecord)
import com.mybatisflex.core.activerecord.Model;
#end
#if(withSwagger && swaggerVersion.getName() == "FOX")
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
import io.swagger.v3.oas.annotations.media.Schema;
#end
#if(withLombok)
#if(withActiveRecord)
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
#else
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
#end
#end

/**
 * #(table.getComment()) 实体
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
#if(withLombok)
#if(withActiveRecord)
@Accessors(chain = true)
@Data(staticConstructor = "create")
@EqualsAndHashCode(callSuper = true)
#else
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
#end
#end
#if(withSwagger && swaggerVersion.getName() == "FOX")
@ApiModel("#(table.getComment())")
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
@Schema(description = "#(table.getComment())")
#end
public class #(entityClassName) extends BaseDTO  {

#for(column : table.columns)
    #set(comment = javadocConfig.formatColumnComment(column.comment))
    #if(isNotBlank(comment))
    /** #(comment) */
    #end
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiModelProperty("#(column.comment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Schema(description = "#(column.comment)")
    #end
    private #(column.propertySimpleType) #(column.property)#if(isNotBlank(column.propertyDefaultValue)) = #(column.propertyDefaultValue)#end;

#end
#if(!withLombok)
    #if(withActiveRecord)
    public static #(entityClassName) create() {
        return new #(entityClassName)();
    }

    #end
    #for(column: table.columns)
    public #(column.propertySimpleType) #(column.getterMethod())() {
        return #(column.property);
    }

    #if(withActiveRecord)
    public #(entityClassName) #(column.setterMethod())(#(column.propertySimpleType) #(column.property)) {
        this.#(column.property) = #(column.property);
        return this;
    }
    #else
    public void #(column.setterMethod())(#(column.propertySimpleType) #(column.property)) {
        this.#(column.property) = #(column.property);
    }
    #end

    #end
#end}
