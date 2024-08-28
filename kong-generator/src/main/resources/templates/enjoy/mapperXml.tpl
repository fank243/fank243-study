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

    <!-- 表名 -->
    <sql id="TABLE_NAME">#(table.name)</sql>

    <!-- 所有字段 -->
    <sql id="Base_Column_List">
        #for(column : table.columns)`#(column.name)`#(for.last ? '' : ',')#else#end

    </sql>

    <!-- 通用查询条件 -->
    <sql id="WHERE_#(entityNameUnderlineCase)">
        #for(column : table.columns)
            <if test="#(entityNameLower).#(column.property) != null and #(entityNameLower).#(column.property) != ''">
                and #(column.name) = #{params.#(column.property),jdbcType=#(column.rawType)}
            </if>
        #end
        #if(logicDeleteName) and is_deleted = false#end
    </sql>

    <!-- 排序 -->
    <sql id="SORT_#(entityNameUnderlineCase)">
        <if test="params.columnSortList != null and params.columnSortList.size() > 0">
          order by
          <foreach collection="params.columnSortList" item="item" separator=",">
              #{item.sortColumn} #{item.sortType}
          </foreach>
      </if>
    </sql>

     <!-- 根据主键查询 -->
    <select id="findBy#(primaryPropertyNameUpper)" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"/> from #(table.name) <where> and #(primaryColumnName) = #{#(primaryPropertyName)} #if(logicDeleteName) and is_deleted = false #end</where>
    </select>

    <!-- 多条件查询(单条记录) -->
    <select id="findByCondition" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from #(table.name)
        <where>
           <include refid="WHERE_#(entityNameUnderlineCase)"/>
        </where>
        <include refid="SORT_#(entityNameUnderlineCase)"/>
        limit 1
    </select>

    <!-- 多条件查询(多条记录) -->
    <select id="findListByCondition" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from #(table.name)
        <where>
            <include refid="WHERE_#(entityNameUnderlineCase)"/>
        </where>
        <include refid="SORT_#(entityNameUnderlineCase)"/>
    </select>
</mapper>
