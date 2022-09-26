package com.fank243.study.system.dao;

import com.fank243.study.api.system.domain.vo.SysPermVO;
import com.fank243.study.system.domain.entity.SysPermEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统权限表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Repository
public interface ISysPermDao extends BaseMapper<SysPermEntity> {


    /**
     * 根据userId查询权限集合
     *
     * @param userId 管理员ID
     * @return 权限集合
     */
    @Select("""
        SELECT a.* FROM `tb_sys_perm` a LEFT JOIN tb_sys_role_perm b ON a.perm_id=b.perm_id LEFT JOIN tb_sys_user_role c ON b.role_id=c.role_id
        WHERE a.status = 0 and c.user_id= #{userId}
        """)
    List<SysPermVO> findByUserId(String userId);

    /**
     * 根据权限类型查询
     *
     * @param permTypes 权限类型
     * @return 权限集合
     */
    @Select("""
        <script>
        select * from tb_sys_perm where status = 0 and perm_type in
        <foreach item="item" index="index" collection="permTypes" open="(" separator="," close=")">
        	#{item}
        </foreach>
        </script>
        """)
    List<SysPermVO> findByPermTypes(@Param("permTypes") List<Integer> permTypes);
}
