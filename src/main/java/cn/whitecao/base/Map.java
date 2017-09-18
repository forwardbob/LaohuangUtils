package cn.whitecao.base;

/**
 * Created by 腼腆的老黄 on 2017/9/14.
 */
public interface Map<K,V> {
    //map的基本功能是快速存取
     V put(K k,V v);
     V get(K k);
     V remove(K k);
     public interface Entry<K,V>{
         //map.getKey
         K getKey();
         V getValue();
     }
}
