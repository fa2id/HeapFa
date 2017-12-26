package com.fa2id.heap;

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private T value;
    private Node<T> leftNode;
    private Node<T> rightNode;
    private Node<T> parentNode;

    public Node(T value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return (rightNode == null && leftNode == null);
    }

    public boolean isFull() {
        return (leftNode != null && rightNode != null);
    }

    public void setChild(Node<T> node) {
        if (leftNode == null) {
            setLeftNode(node);
        } else if (rightNode == null) {
            setRightNode(node);
        }
    }

    public void switchValueWithParent() {
        T temp = value;
        value = parentNode.value;
        parentNode.value = temp;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getChildWithMaxValue() {
        if (!isLeaf()) {
            if (rightNode == null && leftNode != null)
                return leftNode;
            else if (leftNode == null && rightNode != null)
                return rightNode;
            else if (leftNode.compareTo(rightNode) > 0)
                return leftNode;
            else return rightNode;
        }
        return null;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void removeChild() {
        if (rightNode != null)
            rightNode = null;
        else if (leftNode != null)
            leftNode = null;
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public boolean isRoot() {
        return parentNode == null;
    }

    private void setLeftNode(Node<T> leftNode) {
        this.leftNode = leftNode;
        leftNode.setParentNode(this);
    }

    private void setRightNode(Node<T> rightNode) {
        this.rightNode = rightNode;
        rightNode.setParentNode(this);
    }

    public List<Node<T>> getChildren(boolean leftChildFirst) {
        List<Node<T>> children = new ArrayList<>();
        if (leftChildFirst) {
            if (leftNode != null) {
                children.add(leftNode);
            }
            if (rightNode != null) {
                children.add(rightNode);
            }
        } else {
            if (rightNode != null) {
                children.add(rightNode);
            }
            if (leftNode != null) {
                children.add(leftNode);
            }
        }
        return children;
    }

    private void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public int compareTo(Node<T> node) {
        return value.compareTo(node.value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value= " + value +
                ",\tparent= " + (parentNode != null ? parentNode.value : "_") +
                ",\tleftNode= " + (leftNode != null ? leftNode.value : "_") +
                ",\trightNode= " + (rightNode != null ? rightNode.value : "_") +
                '}';
    }
}
