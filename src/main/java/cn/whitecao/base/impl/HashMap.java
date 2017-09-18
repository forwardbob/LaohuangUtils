package cn.whitecao.base.impl;


import cn.whitecao.base.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 腼腆的老黄 on 2017/9/15.
 */
public class HashMap<K, V> implements Map<K, V> {

    //定义数组的默认大小2的n次方
    private static int defaultLength = 2 << 4;
    //扩容因子 useSize/数组长度>defaultAddSizeFactory  JDK7中是0.75
    private static double defaultAddSizeFactory = 0.666f;
    //使用长度
    private int useSize;
    //定义map
    private Entry<K, V>[] table = null;
    //单独定义一个key=null的entry
    Entry<K, V> noKeyEntry = new Entry<K, V>(null, null, null, 0);

    public HashMap() {
        //传说中的门面模式
        this(defaultLength, defaultAddSizeFactory);
    }

    public HashMap(int length, double defaultAddSizeFactory) {
        if (length < 0) {
            throw new IllegalArgumentException("length唔正确");
        }
        if (defaultAddSizeFactory <= 0 || defaultAddSizeFactory > 1) {
            throw new IllegalArgumentException("defaultAddSizeFactory唔正确");
        }
        this.defaultLength = defaultLength;
        this.defaultAddSizeFactory = defaultAddSizeFactory;
        table = new Entry[defaultLength];
    }


    public V put(K key, V value) {
        if (key == null) {
            noKeyEntry.v = value;
            return value;
        }
        if (useSize > defaultLength * defaultAddSizeFactory) {
            up2size();
        }
        int hKey = hash(key.hashCode());
        int index = getTableIndex(hKey, table.length);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            //entry==null说明还没有储存
            entry = new Entry<K, V>(key, value, null, hKey);
            table[index] = entry;
            useSize++;
        } else {
            //entry不为空就放到链表头
            table[index] = new Entry<K, V>(key, value, entry, hKey);
            //  useSize++;
        }
        return table[index].getValue();
    }

    public V get(K key) {
        if (key == null) {
            return noKeyEntry.v;
        }
        int hKey = hash(key.hashCode());
        int index = getTableIndex(hKey, table.length);
        if (table[index] == null) {
            return null;
        }
        return findValueByEqualKey(key, table[index]);
    }

    public V remove(K key) {
        if (key == null) {
            return noKeyEntry.v;
        }
        int hKey = hash(key.hashCode());
        int index = getTableIndex(hKey, table.length);
        if (table[index] == null) {
            return null;
        }
        removeValueByEqualKey(key, table[index],index);
        return null;
    }
    //========================================================================================


    //通过key和数组长度获取下标长度
    private int getTableIndex(int hKey, int length) {
        //length=2^n-1  00001111
        int m = length - 1;
        //index= [0,m);
        // int index = hKey & m;
        return hKey & m;
    }

    /**
     * 扩容
     */
    private void up2size() {
        defaultLength = defaultLength * 2;
        Entry[] newTable = new Entry[defaultLength];
        //对扩容前的数组进行散列
        againHash(newTable);
    }


    private void againHash(Entry<K, V>[] newTable) {
        List<Entry<K, V>> entryList = new ArrayList<Entry<K, V>>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            foundEntryNext(table[i], entryList);
        }
        if (entryList.size() > 0) {
            useSize = 0;
            table = newTable;
            for (Entry<K, V> entry : entryList) {
                //断开原有的链表
                entry.next = null;
                //调用put方法
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 遍历对比key
     *
     * @param key
     * @param entry
     * @return
     */
    private V findValueByEqualKey(K key, Entry<K, V> entry) {
        if (key == entry.getKey() || key.equals(entry.getKey())) {
            return entry.getValue();
        } else if (entry.next != null) {
            return findValueByEqualKey(key, entry.next);
        }
        return null;
    }

    /**
     * 根据key移除entry
     *
     * @param key
     * @param entry
     */
    private void removeValueByEqualKey(K key, Entry<K, V> entry,int index) {
        Entry temp = entry;
        for (entry=entry;entry!=null;temp = entry,entry=entry.next){
            if (!key.equals(entry.getKey())) {
                continue;
            }
            //判断是否是头结点
            if (entry==temp){
                table[index]=entry.next;
            }else {
                //判断是否是尾节点
                if (null==temp.next.next){
                    temp.next=null;
                }else {
                   temp.next=entry.next;
                }
            }
            break;
        }



    }

    /**
     * 递归遍历entry链表,将节点放入entryList中
     *
     * @param entry
     * @param entryList
     */
    private void foundEntryNext(Entry<K, V> entry, List<Entry<K, V>> entryList) {
        if (entry != null && entry.next != null) {
            entryList.add(entry);
            //递归
            foundEntryNext(entry.next, entryList);
        } else {
            entryList.add(entry);
        }
    }

    //s自己的hash算法
    private int hash(int hashCode) {
        hashCode = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 11));
        return hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 11));
    }

    static Random random = new Random();

    public static void main(String[] args) {
        Map map = new HashMap();
        for (int i = 0; i < 1000; i++) {
            map.put("key" + i, i);
            if (i % 1000000 == 0) {
                System.out.println(i);
            }
        }
//        map.remove("key684");
//        map.remove("key563");
        map.remove("key321");
        return;
//        long startTime = System.currentTimeMillis();
//        System.out.println("start:" + startTime);
//        for (int i = 0; i < 10000; i++) {
//            map.get("key1000" + i);
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("endTime:" + endTime);
//        System.out.println(endTime - startTime);
    }

    class Entry<K, V> implements Map.Entry<K, V> {
        private K k;
        private V v;
        Entry<K, V> next;
        int hash;

        public Entry(K k, V v, Entry next, int hash) {
            this.k = k;
            this.v = v;
            this.next = next;
            this.hash = hash;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }
    }

}
