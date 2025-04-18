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

package com.github.fank243.kong.core.constants;

/**
 * 时间常量，均换算成秒为最小单位
 * 
 * @author FanWeiJie
 * @since 2021-06-08 22:49:20
 */
public class TimeConstants {

    /** 1min **/
    public static final int MINUTE_1 = 60;

    /** 5min **/
    public static final int MINUTE_5 = 5 * MINUTE_1;

    /** 10min **/
    public static final int MINUTE_10 = 10 * MINUTE_1;

    /** 30min **/
    public static final int MINUTE_30 = 30 * MINUTE_1;

    /** 1h **/
    public static final int HOUR_1 = 3600;

    /** 2h **/
    public static final int HOUR_2 = 2 * HOUR_1;

    /** 3h **/
    public static final int HOUR_3 = 3 * HOUR_1;

    /** 1天 **/
    public static final int DAY_1 = 86400;

    /** 7天 **/
    public static final int DAY_7 = 7 * DAY_1;

    /** 30天 **/
    public static final int DAY_30 = 30 * DAY_1;

}
