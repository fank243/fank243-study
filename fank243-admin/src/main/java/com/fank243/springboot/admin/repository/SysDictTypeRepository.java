package com.fank243.springboot.admin.repository;

import com.fank243.springboot.core.entity.SysDictType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典类别表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:53:51
 */
@Repository
public interface SysDictTypeRepository extends JpaRepository<SysDictType, Long> {
    /**
     * find all SysDictType
     * 
     * @return SysDictType Collections
     */
    List<SysDictType> findByStatus(int status);

}
