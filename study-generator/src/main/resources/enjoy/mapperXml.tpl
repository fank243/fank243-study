<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="#(packageConfig.mapperPackage).#(table.buildMapperClassName())">
    <resultMap id="BaseResultMap" type="#(packageConfig.entityPackage).#(table.buildEntityClassName())">
        #for(column : table.columns)
        #if(column.isPrimaryKey())
        <id column="#(column.name)" jdbcType="#(column.rawType)" property="#(column.property)"/>
        #else
        <result column="#(column.name)" jdbcType="#(column.rawType)" property="#(column.property)"/>
        #end
        #end
    </resultMap>

    <sql id="TABLE_NAME">#(table.name)</sql>
    <sql id="Base_Column_List">
        #for(column : table.columns)`#(column.name)`#(for.last ? '' : ',')#else#end

    </sql>

    <select id="findBy#(primaryPropertyNameUpper)" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from #(table.name)
        <where> and #(primaryColumnName) = #{#(primaryPropertyName)} #if(logicDeleteName) and is_deleted = false #end
        </where>
    </select>

    <select id="findByCondition" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from #(table.name)
        <where>
            #for(column : table.columns)
            <if test="#(entityNameLower).#(column.property) != null and #(entityNameLower).#(column.property) != ''">
                and #(column.name) = #{#(entityNameLower).#(column.property),jdbcType=#(column.rawType)}
            </if>
            #end
            #if(logicDeleteName) and is_deleted = false #end
        </where>
        limit 1
    </select>

    <select id="findListByCondition" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from #(table.name)
        <where>
            #for(column : table.columns)
            <if test="#(entityNameLower).#(column.property) != null and #(entityNameLower).#(column.property) != ''">
                and #(column.name) = #{#(entityNameLower).#(column.property),jdbcType=#(column.rawType)}
            </if>
            #end
            #if(logicDeleteName) and is_deleted = false #end
        </where>
    </select>
</mapper>
