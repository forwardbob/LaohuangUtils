package cn.whitecao.base.impl;


import cn.whitecao.base.List;

/**
 * Created by 腼腆的老黄 on 2017/9/13.
 */
public class LinkedList<E> implements List<E>, java.io.Serializable {

    /**
     * size
     */
    transient int size = 0;

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
     * @param e
     * @return
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 将指定元素添加到头部
     * @param e
     * @return
     */
    public boolean addFirst(E e) {
        linkFirst(e);
        return true;
    }

    /**
     * 将list转成数组
     * @return
     */
    public Object[] toArray(){
        Node<E> firstNode=first;
        Object[] objects=new Object[size];
        int i=0;
        for (firstNode=firstNode;firstNode.next!=null;firstNode=firstNode.next){
            objects[i++]=firstNode;
        }
        return objects;
    }


    /**
     * 将e添加到链表末尾
     * @param e
     */
    void linkLast(E e){
        final Node<E> lastNode=last;
        final Node<E> newNode=new Node<E>(e,null,lastNode);
        last=newNode;
        //判断lastNode是否等于null
        if (lastNode==null){
            first=newNode;
        }else{
            lastNode.next=newNode;
        }
        size++;
        modCount++;
    }
    /**
     * 将e添加到链表头部
     * @param e
     */
    void linkFirst(E e){
        final Node<E> firstNode=first;
        final Node<E> newNode=new Node<E>(e,firstNode,null);
        if (firstNode!=null){
            firstNode.pre=newNode;
        }else {
            //如果头结点为空 那么头尾节点都是新节点
            last=newNode;
        }
        first=newNode;
        size++;
        modCount++;
    }

    public static class Node<E>{
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
