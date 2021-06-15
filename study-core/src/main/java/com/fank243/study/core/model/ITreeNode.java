package com.fank243.study.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * 树形结构体
 * 
 * @author FanWeiJie
 * @date 2021-06-08 22:42:53
 */
public interface ITreeNode<T> extends Serializable {

    /**
     * 节点ID
     * 
     * @return 节点ID
     */
    T id();

    /**
     * 父节点ID
     *
     * @return 父节点ID
     */
    T pid();

    /**
     * 是否是根节点
     *
     * @return true：根节点
     */
    boolean isRoot();

    /**
     * 序号
     *
     * @return 序号
     */
    int sort();

    /**
     * 设置子节点
     *
     * @param children 子节点
     */
    void setChildren(List<? extends ITreeNode<T>> children);

    /**
     * 获取所有子节点
     *
     * @return 子节点列表
     */
    List<? extends ITreeNode<T>> getChildren();
}
