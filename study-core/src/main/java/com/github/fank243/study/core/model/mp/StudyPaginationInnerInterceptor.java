// package com.github.fank243.study.core.model.mp;
//
// import java.sql.SQLException;
//
// import org.apache.ibatis.executor.Executor;
// import org.apache.ibatis.mapping.BoundSql;
// import org.apache.ibatis.mapping.MappedStatement;
// import org.apache.ibatis.session.ResultHandler;
// import org.apache.ibatis.session.RowBounds;
//
// import com.baomidou.mybatisplus.annotation.DbType;
// import com.baomidou.mybatisplus.core.metadata.IPage;
// import com.baomidou.mybatisplus.core.toolkit.ParameterUtils;
// import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
// import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
//
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;
//
/// **
// * MyBatis Plus 分页拦截器
// *
// * @author FanWeiJie
// * @since 2022-09-30 21:22:48
// */
// @EqualsAndHashCode(callSuper = false)
// @NoArgsConstructor
// @Data
// public class StudyPaginationInnerInterceptor extends PaginationInnerInterceptor {
//
// /**
// * 数据库类型 查看 {@link #findIDialect(Executor)} 逻辑
// */
// private DbType dbType;
//
// /**
// * 方言实现类 查看 {@link #findIDialect(Executor)} 逻辑
// */
// private IDialect dialect;
//
// public StudyPaginationInnerInterceptor(DbType dbType) {
// this.dbType = dbType;
// }
//
// public StudyPaginationInnerInterceptor(IDialect dialect) {
// this.dialect = dialect;
// }
//
// @Override
// public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
// ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
// IPage<?> page = ParameterUtils.findPage(parameter).orElse(null);
// // size 小于 0 直接设置为 10
// if (page != null && page.getSize() <= 0) {
// page.setSize(MybatisConstants.DEFAULT_PAGE_SIZE);
// }
// super.beforeQuery(executor, ms, page, rowBounds, resultHandler, boundSql);
// }
//
// }