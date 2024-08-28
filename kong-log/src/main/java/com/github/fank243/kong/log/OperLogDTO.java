/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.log;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 请求响应日志表
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Schema(description = "操作日志请求实体")
@Data
public class OperLogDTO implements Serializable {

    /** 日志唯一ID */
    @Schema(description = "操作日志ID")
    private String logId;

    /** 租户 */
    @Schema(description = "租户")
    private String tenant;

    /** 保存的操作日志的类型 */
    @Schema(description = "保存的操作日志的类型")
    private String logType;

    /** 日志绑定的业务标识 */
    @Schema(description = "日志绑定的业务标识")
    private String bizNo;

    /** 日志的子类型 */
    @Schema(description = "日志的子类型")
    private String subType;

    /** 操作人ID */
    @Schema(description = "操作人ID")
    private String operatorId;

    /** 日志内容 */
    @Schema(description = "日志内容")
    private String content;

    /** 扩展信息 */
    @Schema(description = "扩展信息")
    private String extra;

    /** 日志操作时间 */
    @Schema(description = "日志操作时间", hidden = true)
    private Date createTime;

    /** 当前页码 **/
    private Integer currPage;

    /** 页记录数 **/
    private Integer pageSize;

    public long getCurrPage() {
        return currPage == null || currPage <= 0 ? 1 : currPage;
    }

    public long getPageSize() {
        if (pageSize == null || pageSize <= 0) {
            return 10;
        }
        return pageSize > 100 ? 100 : pageSize;
    }

    private List<ColumnSort> columnSortList;

    @Data
    static class ColumnSort {
        private String sortColumn;
        private String sortType;
    }
}
