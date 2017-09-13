package cn.whitecao.base.impl;


import cn.whitecao.base.List;
import org.junit.Test;

/**
 * Created by 腼腆的老黄 on 2017/9/13.
 */

public class LinkedListTest {
    LinkedList<Integer> list=new LinkedList<Integer>();
    @Test
    public void addFirst() throws Exception {
        list=new LinkedList<Integer>();
        for (int i=100000;i>0;i--) {
            list.addFirst(i);
        }
        System.out.println(list.size);
    }

    @Test
    public void add() throws Exception {
        List<Integer> list=new LinkedList<Integer>();
        for (int i=0;i<100000;i++) {

            list.add(i);
        }
        Object[] o=list.toArray();
       // System.out.println(list.size);
    }



}
