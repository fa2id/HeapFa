package com.fa2id.heap;

import java.util.LinkedList;
import java.util.Queue;

public class Heap<T extends Comparable<T>> {

    private Node<T> root;
    private Node<T> lastParentNode;
    private Node<T> lastAddedNode;
    private Queue<Node<T>> queueForLastParentNode = new LinkedList<>();

    public void insert(T value) {
        Node<T> node = new Node<>(value);
        if (root == null) {
            root = node;
            queueForLastParentNode.offer(root);
            lastAddedNode = root;
        } else {
            getLastParentNode().setChild(node);
            updateAfterInserting(node);
            lastAddedNode = node;
            queueForLastParentNode.offer(node);
        }
    }

    public boolean remove() {
        if (root != null) {
            if (root.isLeaf()) {
                root = null;
                return true;
            }
            T lastValue = lastAddedNode.getValue();
            root.setValue(lastValue);
            lastAddedNode.getParentNode().removeChild();
            updateAfterRemoving();
            return true;
        } else return false;
    }

    public void removeAll() {
        root = null;
    }

    public T poll() {
        if (root == null) {
            return null;
        } else {
            if (root.isLeaf())
                return root.getValue();
            T result = root.getValue();
            T lastValue = lastAddedNode.getValue();
            root.setValue(lastValue);
            lastAddedNode.getParentNode().removeChild();
            updateAfterRemoving();
            return result;
        }
    }

    public T peek() {
        return root != null ? root.getValue() : null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node<T> getLastParentNode() {
        if (lastParentNode == null || lastParentNode.isFull()) {
            lastParentNode = queueForLastParentNode.poll();
        }
        return lastParentNode;
    }

    private void updateAfterRemoving() {
        Node<T> node = root;
        while (node.compareTo(node.getChildWithMaxValue()) < 0) {
            Node<T> maxChild = node.getChildWithMaxValue();
            maxChild.switchValueWithParent();
            if (maxChild.isLeaf())
                break;
            else
                node = maxChild;
        }
    }

    private void updateAfterInserting(Node<T> node) {
        while (node.compareTo(node.getParentNode()) > 0) {
            node.switchValueWithParent();
            if (node.getParentNode().isRoot())
                break;
            else
                node = node.getParentNode();
        }
    }

    @Override
    public String toString() {
        if (root == null)
            return "Empty heap!";
        StringBuilder result = new StringBuilder();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> nodePointer = queue.poll();
            result.append(nodePointer);
            if (!nodePointer.isLeaf()) queue.addAll(nodePointer.getChildren(true));
            result.append("\n");
        }
        return result.toString();
    }
}
