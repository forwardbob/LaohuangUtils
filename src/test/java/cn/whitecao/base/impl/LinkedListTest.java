package cn.whitecao.base.impl;


import cn.whitecao.base.List;
import org.junit.Test;

/**
 * Created by 腼腆的老黄 on 2017/9/13.
 */

public class LinkedListTest {



    @Test
    public void getFirst() throws Exception {
        for (int i=100000;i>0;i--) {
            list.addFirst(i);
        }
        Integer first = list.getFirst();
        Integer last = list.getLast();
    }

    @Test
    public void getLast() throws Exception {
    }

    LinkedList<Integer> list=new LinkedList<Integer>();
    @Test
    public void addFirst() throws Exception {
        list=new LinkedList<Integer>();
        for (int i=1000;i>0;i--) {
            list.addFirst(i);
        }
        System.out.println(list.size());
    }

    @Test
    public void add() throws Exception {
        List<Integer> list=new LinkedList<Integer>();
        for (int i=0;i<1000;i++) {

            list.add(i);
        }
        Object[] o=list.toArray();
       // System.out.println(list.size);
    }

    @Test
    public void get() throws Exception {
        for (int i=0;i<100000;i++) {
            list.add(i);
        }
        System.out.println(list.get(10));
        System.out.println(list.get(50000));
        System.out.println(list.get(49999));
        System.out.println(list.get(99999));
    }
    @Test
    public void t(){
        LinkedList linkedList=new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.removeFirst();
        new String("");
    }
}
