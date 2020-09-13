package com.fank243.springboot.admin.repository;

import com.fank243.springboot.core.entity.SysDictData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典数据表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:53:51
 */
@Repository
public interface SysDictDataRepository extends JpaRepository<SysDictData, Long> {

    /**
     * SysDictData
     * 
     * @param dictType 字典类别
     * @return SysDictData
     */
    List<SysDictData> findByDictTypeAndStatusOrderByDictSort(String dictType, int status);

    /**
     * dict label
     *
     * @param dictType 字典类别
     * @param dictValue 字典value
     * @return dict label
     */
    @Query(value = "select dict_label from tb_sys_dict_data where dict_type =:dictType and dict_value=:dictValue",
        nativeQuery = true)
    String findByDictTypeAndDictValue(@Param("dictType") String dictType,@Param("dictValue") Object dictValue);
}
