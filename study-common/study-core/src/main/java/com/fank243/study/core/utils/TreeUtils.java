package com.fank243.study.core.utils;

import cn.hutool.core.collection.CollUtil;
import com.fank243.study.core.model.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 树形结构体工具类
 * 
 * @author FanWeiJie
 * @date 2021-06-08 22:55:35
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class TreeUtils {

    /**
     * 根据所有树节点列表，生成含有所有树形结构的列表
     *
     * @param nodes 树形节点列表
     * @param <T> 节点类型
     * @return 树形结构列表
     */
    public static <T extends TreeNode<?>> List<T> generateTree(List<T> nodes) {
        List<T> roots = new ArrayList<>();
        for (Iterator<T> ite = nodes.iterator(); ite.hasNext();) {
            T node = ite.next();
            if (node.isRoot()) {
                roots.add(node);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }

        roots.forEach(r -> {
            setChildren(r, nodes);
        });
        return roots;
    }

    /**
     * 从所有节点列表中查找并设置parent的所有子节点
     *
     * @param parent 父节点
     * @param nodes 所有树节点列表
     */
    private static <T extends TreeNode> void setChildren(T parent, List<T> nodes) {
        List<T> children = new ArrayList<>();
        Object pid = parent.id();
        for (Iterator<T> iterator = nodes.iterator(); iterator.hasNext();) {
            T node = iterator.next();
            if (Objects.equals(node.pid(), pid)) {
                children.add(node);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                iterator.remove();
            }
        }
        // 如果孩子为空，则直接返回,否则继续递归设置孩子的孩子
        if (children.isEmpty()) {
            return;
        }
        parent.setChildren(children);
        children.forEach(m -> {
            // 递归设置子节点
            setChildren(m, nodes);
        });
    }

    /**
     * 获取指定树节点下的所有叶子节点
     *
     * @param parent 父节点
     * @param <T> 实际节点类型
     * @return 叶子节点
     */
    public static <T extends TreeNode<?>> List<T> getLeafList(T parent) {
        List<T> leafList = new ArrayList<>();
        getLeafList(parent, leafList);
        return leafList;
    }

    /**
     * 将父节点的所有叶子节点填充至leafList列表中
     *
     * @param parent 父节点
     * @param leafList 子节点列表
     * @param <T> 实际节点类型
     */
    private static <T extends TreeNode> void getLeafList(T parent, List<T> leafList) {
        List<T> children = parent.getChildren();
        // 如果节点没有子节点则说明为叶子节点
        if (CollUtil.isEmpty(children)) {
            leafList.add(parent);
            return;
        }
        // 递归调用子节点，查找叶子节点
        for (T child : children) {
            getLeafList(child, leafList);
        }
    }
}
