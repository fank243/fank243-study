package com.fank243.springboot.admin.service.base;

import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.common.utils.ClassUtils;
import com.fank243.springboot.common.utils.GenericsUtils;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.entity.base.BaseEntity;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BaseService
 * 
 * @author FanWeiJie
 * @date 2020-03-24 20:05:26
 */
@SuppressWarnings("unchecked")
@Slf4j
@Service
public abstract class BaseService<T extends BaseEntity> {

    @Resource
    private EntityManager em;

    /** 数据库表实体 */
    protected Class<T> entityClass;

    public BaseService() {
        entityClass = GenericsUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 查询条件参数赋值 > 命名参数
     * 
     * @param query {@link Query}，SQL示例：{@code id =:id}
     * @param args 条件参数，示例：{@code id = 1}
     */
    void setParameter(Query query, Map<String, Object> args) {
        if (args != null && args.keySet().size() > 0) {
            Set<Map.Entry<String, Object>> entries = args.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 查询条件参数赋值 > 占位符
     *
     * @param query {@link Query}，SQL示例：{@code id =?1 AND name =?2}
     * @param params 条件参数，示例：{@code 1,李斯}
     */
    void setParameter(Query query, Object... params) {
        if (params == null || params.length <= 0) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
    }

    /**
     * 条件查询多个数据库实体 > 分页
     * 
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @param firstResult 当前页码
     * @param maxResult 页记录数
     * @return 集合
     */
    protected List<T> findAllBySql(String sql, Map<String, Object> args, int firstResult, int maxResult) {
        Query query = em.createNativeQuery(sql, entityClass);
        setParameter(query, args);
        if (firstResult > 0 || maxResult > 0) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
        }
        return ClassUtils.cast(query.getResultList());
    }

    /**
     * 条件查询多个数据库实体
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected List<T> findAllBySql(String sql, Map<String, Object> args) {
        return findAllBySql(sql, args, 0, 0);
    }

    /**
     * 条件查询单个数据库实体
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected T findBySql(String sql, Map<String, Object> args) {
        List<T> list = findAllBySql(sql, args);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 条件查询多个实体JavaBean对象，该对象需要添加添加类注解{@code Entity} 同时需要添加主键ID注解{@code Id}
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @param firstResult 当前页码
     * @param maxResult 页记录数
     * @return 集合
     */
    protected <B> List<B> findAllBeanBySql(String sql, Class<B> clazz, Map<String, Object> args, int firstResult,
        int maxResult) {
        Query query = em.createNativeQuery(sql, clazz);
        setParameter(query, args);
        if (firstResult > 0 || maxResult > 0) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
        }
        return ClassUtils.cast(query.getResultList());
    }

    /**
     * 条件查询多个实体JavaBean对象，该对象需要添加添加类注解{@code Entity} 同时需要添加主键ID注解{@code Id}
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected <B> List<B> findAllBeanBySql(String sql, Class<B> clazz, Map<String, Object> args) {
        return findAllBeanBySql(sql, clazz, args, 0, 0);
    }

    /**
     * 条件查询单个实体JavaBean对象，该对象需要添加添加类注解{@code Entity} 同时需要添加主键ID注解{@code Id}
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected <B> B findBeanBySql(String sql, Class<B> clazz, Map<String, Object> args) {
        List<B> list = findAllBeanBySql(sql, clazz, args);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 条件查询多个Map对象集合
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @param firstResult 当前页码
     * @param maxResult 页记录数
     * @return 集合
     */
    protected List<Map<String, Object>> findListMapBySql(String sql, Map<String, Object> args, int firstResult,
        int maxResult) {
        Query query = em.createNativeQuery(sql);
        setParameter(query, args);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (firstResult > 0 || maxResult > 0) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
        }
        return ClassUtils.cast(query.getResultList());
    }

    /**
     * 条件查询多个Map对象集合
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected List<Map<String, Object>> findListMapBySql(String sql, Map<String, Object> args) {
        return findListMapBySql(sql, args, 0, 0);
    }

    /**
     * 条件查询单个Map对象集合
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected Map<String, Object> findMapBySql(String sql, Map<String, Object> args) {
        List<Map<String, Object>> list = findListMapBySql(sql, args);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 条件查询数据表中的单个字段多条记录
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected List<Object> findListSingleBySql(String sql, Map<String, Object> args) {
        Query query = em.createNativeQuery(sql);
        setParameter(query, args);
        return ClassUtils.cast(query.getResultList());
    }

    /**
     * 条件查询数据表中的单个字段单条记录
     *
     * @param sql SQL
     * @param args 命名参数，示例：{@code name =:name}
     * @return 集合
     */
    protected Object findSingleBySql(String sql, Map<String, Object> args) {
        List<Object> list = findListSingleBySql(sql, args);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 条件查询数据表中的单个字段
     *
     * @param sql SQL
     * @param defaultValue 返回结果为NULL时，返回默认值
     * @param args 命名参数，示例：{@code name =:name}
     * @return 结果
     */
    protected int findSingleInt(String sql, int defaultValue, Map<String, Object> args) {
        Object obj = findSingleBySql(sql, args);
        return obj == null ? defaultValue : StrUtils.strToInt(obj.toString(), defaultValue);
    }

    /**
     * 条件查询数据表中的单个字段
     *
     * @param sql SQL
     * @param defaultValue 返回结果为NULL时，返回默认值
     * @param args 命名参数，示例：{@code name =:name}
     * @return 结果
     */
    protected long findSingleLong(String sql, long defaultValue, Map<String, Object> args) {
        Object obj = findSingleBySql(sql, args);
        return obj == null ? defaultValue : StrUtils.strToLong(obj.toString(), defaultValue);
    }

    /**
     * 条件查询数据表中的单个字段
     *
     * @param sql SQL
     * @param defaultValue 返回结果为NULL时，返回默认值
     * @param args 命名参数，示例：{@code name =:name}
     * @return 结果
     */
    protected double findSingleDouble(String sql, double defaultValue, Map<String, Object> args) {
        Object obj = findSingleBySql(sql, args);
        return obj == null ? defaultValue : StrUtils.strToDouble(obj.toString(), defaultValue);
    }

    /**
     * 分页查询数据表数据 > 表实体
     * 
     * @param pageInfo {@link PageInfo}
     * @param countSql 统计总条数SQL
     * @param querySql 查询SQL
     * @param conditionArgs 查询条件参数
     * @return 数据集合
     */
    protected PageBean<T> pageOfBySql(PageInfo pageInfo, String countSql, String querySql,
        Map<String, Object> conditionArgs) {
        PageBean<T> page = new PageBean<>();

        page.setCurrPage(pageInfo.getPage());
        page.setPageSize(pageInfo.getLimit());

        int count = findSingleInt(countSql, 0, conditionArgs);
        page.setTotalCount(count);
        if (count == 0) {
            return page;
        }

        int firstResult = (page.getCurrPage() - 1) * page.getPageSize();
        page.setList(findAllBySql(querySql, conditionArgs, firstResult, page.getPageSize()));

        return page;
    }

    /**
     * 分页查询数据表数据 > JavaBean对象，该对象需要添加添加类注解{@code Entity} 同时需要添加主键ID注解{@code Id}
     *
     * @param pageInfo {@link PageInfo}
     * @param countSql 统计总条数SQL
     * @param querySql 查询SQL
     * @param conditionArgs 查询条件参数
     * @return 数据集合
     */
    protected <B> PageBean<B> pageOfBeanBySql(PageInfo pageInfo, String countSql, String querySql, Class<B> clazz,
        Map<String, Object> conditionArgs) {
        PageBean<B> page = new PageBean<>();

        page.setCurrPage(pageInfo.getPage());
        page.setPageSize(pageInfo.getLimit());

        int count = findSingleInt(countSql, 0, conditionArgs);
        page.setTotalCount(count);
        if (count == 0) {
            return page;
        }
        int firstResult = (page.getCurrPage() - 1) * page.getPageSize();
        page.setList(findAllBeanBySql(querySql, clazz, conditionArgs, firstResult, page.getPageSize()));

        return page;
    }

    /**
     * 执行增、、删、改语句
     * 
     * @param sql SQL
     * @param args 命令参数，示例：{@code id =:id and name =:name}
     * @return 执行结果，正确返回成功执行的行数，失败返回0
     */
    int executeSQL(String sql, Map<String, Object> args) {
        if (log.isDebugEnabled()) {
            log.debug(sql, JSONObject.toJSONString(args));
        }
        Query query = em.createNativeQuery(sql);
        setParameter(query, args);
        int row = query.executeUpdate();
        if (row > 0) {
            em.clear();
        }
        return row;
    }
}
