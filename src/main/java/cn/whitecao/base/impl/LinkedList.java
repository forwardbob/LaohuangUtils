package cn.whitecao.base.impl;


import cn.whitecao.base.List;

import java.util.NoSuchElementException;

/**
 * Created by 腼腆的老黄 on 2017/9/13.
 */
public class LinkedList<E> implements List<E>, java.io.Serializable {

    /**
     * size
     */
    private transient int size = 0;

    /**
     * 指向第一个节点
     */
    transient Node<E> first;

    /**
     * 指向最后一个节点.
     */
    transient Node<E> last;

    /**
     * 变化次数
     */
    protected transient int modCount = 0;

    public LinkedList() {
    }


    /**
     * 将指定元素添加到最后
     *
     * @param e
     * @return
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 将指定元素添加到头部
     *
     * @param e
     * @return
     */
    public boolean addFirst(E e) {
        linkFirst(e);
        return true;
    }

    /**
     * 将list转成数组
     *
     * @return
     */
    public Object[] toArray() {
        Node<E> firstNode = first;
        Object[] objects = new Object[size];
        int i = 0;
        for (firstNode = firstNode; firstNode.next != null; firstNode = firstNode.next) {
            objects[i++] = firstNode;
        }
        return objects;
    }

    /**
     * 获取头节点
     *
     * @return
     */
    public E getFirst() {
        final Node<E> firstNode = first;
        return firstNode == null ? null : firstNode.item;
    }

    /**
     * 获取尾节点
     *
     * @return
     */
    public E getLast() {
        final Node<E> lastNode = last;
        return lastNode == null ? null : lastNode.item;
    }

    /**
     * 根据下标获取对应的节点
     *
     * @param index
     * @return
     */
    public E get(int index) {
        checkIndex(index);
        return getNode(index).item;
    }

    public boolean remove(Object o) {
        return false;
    }

    public E removeFirst() {
        Node<E> node=first;
        if (node==null){
          throw new NoSuchElementException();
        }
        E e = unlink(node);
        return e;
    }
    public E removeLast() {
        Node<E> node=last;
        if (node==null){
            throw new NoSuchElementException();
        }
        E e = unlink(node);
        return e;
    }
    public E remove(int index) {
        checkIndex(index);
        E e = unlink(getNode(index));
        return e;
    }

    public int size() {
        return size;
    }
    //=====================================================================================

    E unlink(Node<E> node) {
        Node<E> preNode = node.pre;
        Node<E> nextNode = node.next;
        //pre==null说明是头节点
        if (preNode == null) {
            //把头标记设置成next
            first = nextNode;
        } else {
            preNode.next = nextNode;
            node.pre=null;
        }

        if (nextNode == null) {
            last = preNode;
        } else {
            nextNode.pre = preNode;
            node.next=null;
        }
        modCount++;
        size--;
        return node.item;
    }

    /**
     * 将e添加到链表末尾
     *
     * @param e
     */
    void linkLast(E e) {
        final Node<E> lastNode = last;
        final Node<E> newNode = new Node<E>(e, null, lastNode);
        last = newNode;
        //判断lastNode是否等于null
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 将e添加到链表头部
     *
     * @param e
     */
    void linkFirst(E e) {
        final Node<E> firstNode = first;
        final Node<E> newNode = new Node<E>(e, firstNode, null);
        if (firstNode != null) {
            firstNode.pre = newNode;
        } else {
            //如果头结点为空 那么头尾节点都是新节点
            last = newNode;
        }
        first = newNode;
        size++;
        modCount++;
    }

    /**
     * 根据下标返回对应的节点
     *
     * @param index
     * @return
     */
    Node<E> getNode(int index) {
        //二分法
        if (index < size / 2) {
            Node node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.pre;
            }
            return node;
        }
    }

    /**
     * 检查下标是否合法
     *
     * @param index
     */
    void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    String outOfBoundsMsg(int index) {
        return "size=" + size + "   index=" + index;
    }


    public static class Node<E> {
        E item;
        Node<E> next;
        Node<E> pre;

        public Node(E item, Node<E> next, Node<E> pre) {
            this.item = item;
            this.next = next;
            this.pre = pre;
        }

    }
}
