/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.core.domain.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页对象
 *
 * @author FanWeiJie
 * @since 2021-06-15 19:27:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> implements Serializable {

    /**
     * 当前页码，默认：1
     */
    protected long currPage = 1;

    /**
     * 每页显示条数，默认 10
     */
    protected long pageSize = 10;

    /**
     * 总记录数
     */
    protected long totalCount = 0;

    /**
     * 总页数
     */
    protected long totalPage = 0;

    /**
     * 数据集
     */
    protected List<T> data = Collections.emptyList();
}
