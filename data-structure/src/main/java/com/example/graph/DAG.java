package com.example.graph;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Classname DAG
 * @Description 线程安全的有向无环图实现
 * @Date 2021/4/8 11:03 下午
 * @Author by chenyuxiang
 */
public class DAG<Node, NodeInfo> {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 节点
     */
    private Map<Node, NodeInfo> nodes;

    /**
     * 边
     */
    private Map<Node, Set<Node>> edges;

    /**
     * 反向的边
     */
    private Map<Node, Set<Node>> reverseEdges;

    public DAG() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        this.reverseEdges = new HashMap<>();
    }

    public void addNode(Node node, NodeInfo nodeInfo) {
        lock.writeLock().lock();
        try {
            nodes.put(node, nodeInfo);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * @Description: 添加边信息
     * @param fromNode
     * @param toNode
     * @return boolean
     * @author chenyuxiang
     * @date 2021/4/14 10:13 下午
     */
    public boolean addEdge(Node fromNode, Node toNode) {
        lock.writeLock().lock();
        try {
            if (!isLegalAdd(fromNode, toNode)) {
                return false;
            }
            addEdge(fromNode, toNode, edges);
            addEdge(fromNode, toNode, reverseEdges);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * @Description: 添加边信息的具体实现
     * @param fromNode
     * @param toNode
     * @param edges
     * @return void
     * @author chenyuxiang
     * @date 2021/4/14 10:12 下午
     */
    private void addEdge(Node fromNode, Node toNode, Map<Node, Set<Node>> edges) {
        edges.putIfAbsent(fromNode, new HashSet<>());
        Set<Node> toNodes = edges.get(fromNode);
        toNodes.add(toNode);

    }

    private void addNodeIfAbsent(Node fromNode) {
        if (nodes.containsKey(fromNode));
    }

    /**
     * @Description: 是否可添加边
     * @param fromNode
     * @param toNode
     * @return boolean
     * @author chenyuxiang
     * @date 2021/4/14 10:06 下午
     */
    private boolean isLegalAdd(Node fromNode, Node toNode) {
        if (fromNode.equals(toNode) || !nodes.containsKey(fromNode) || !nodes.containsKey(toNode)) {
            return false;
        }
        int vertexCount = nodes.size();

        Queue<Node> queue = new LinkedList<>();
        queue.add(toNode);
        while (!queue.isEmpty() && (vertexCount--) > 0) {
            Node key = queue.poll();
            for (Node subsequentNode : getSubsequentNodes(key)) {
                if (subsequentNode.equals(fromNode)) {
                    return false;
                }
                queue.add(subsequentNode);
            }
        }
        return true;
    }

    
    /**
     * @Description: 获取后续节点
     * @param node
     * @return java.lang.Iterable<? extends Node>
     * @author chenyuxiang
     * @date 2021/4/14 10:06 下午
     */
    public Iterable<? extends Node> getSubsequentNodes(Node node) {
        lock.readLock().lock();
        try {
            return getNeighborNode(node, edges);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * @Description: 获取前置节点信息
     * @param node
     * @return java.lang.Iterable<? extends Node>
     * @author chenyuxiang
     * @date 2021/4/14 10:16 下午
     */
    public Iterable<? extends Node> getPostNodes(Node node) {
        lock.readLock().lock();
        try {
            return getNeighborNode(node, reverseEdges);
        } finally {
            lock.readLock().unlock();
        }
    }
    /**
     * @Description: 获取相连节点集合
     * @param node
     * @param edges
     * @return java.lang.Iterable<? extends Node>
     * @author chenyuxiang
     * @date 2021/4/14 10:05 下午
     */
    private Iterable<? extends Node> getNeighborNode(Node node, Map<Node, Set<Node>> edges) {
        return edges.getOrDefault(node, Collections.EMPTY_SET);
    }
}