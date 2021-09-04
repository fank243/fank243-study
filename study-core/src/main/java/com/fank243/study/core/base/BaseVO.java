package com.fank243.study.core.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * BaseVO
 * 
 * @author FanWeiJie
 * @since 2021-06-15 19:27:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO implements Serializable {

    private Long id;
}
