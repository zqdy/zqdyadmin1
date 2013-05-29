package com.zqdy.core.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class CollectionUtils {

    public static boolean isEmpty(Collection coll){
        return coll == null || coll.isEmpty();
    }
    
    public static boolean isNotEmpty(Collection coll){
        return !isEmpty(coll);
    }

    /**
     * 鍒ゆ柇Collection鏄惁涓嶄负�?
     * @param e
     * @return
     */
    static public boolean notEmpty(Collection collection) {
        return !empty(collection);
    }


    /**
     * 鍒ゆ柇Map鏄惁涓嶄负�?
     * @param map
     * @return
     */
    static public boolean notEmpty(Map map) {
        return !empty(map);
    }

    /**
     * 鍒ゆ柇Object[]鏄惁涓嶄负�?
     * @param os
     * @return
     */
    static public boolean notEmpty(Object[] os) {
        return !empty(os);
    }

   

    public static boolean empty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean empty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean empty(Object[] os) {
        return os == null || os.length == 0;
    }

    /**
     * 鎶奍terator杞崲涓篖ist
     * 
     * @param it
     *            Iterator
     * @return List
     */
    public static List toList(Iterator it) {
        ArrayList<Object> r = new ArrayList<Object>();
        while (it.hasNext()) {
            r.add(it.next());
        }
        return r;
    }

//    public static <V> List<V> toList(List lst, Caster<V> co) {
//        return toList(lst.iterator(), co);
//    }
//
//    public static <V> List<V> toList(Iterator it, Caster<V> co) {
//        List<V> r = new ArrayList<V>();
//        while (it.hasNext()) {
//            r.add(co.cast(it.next()));
//        }
//        return r;
//    }

    public static <V> List<V> toList(Iterator it, Class<V> cls) {
        List<V> r = new ArrayList<V>();
        while (it.hasNext()) {
            r.add(cls.cast(it.next()));
        }
        return r;
    }

    @SuppressWarnings("unchecked")
    public static <V> List<V> toList(List it, Class<V> cls) {
        return (List<V>) it;
    }

    /**
     * 鍚堝苟涓や釜List鐨勬暟鎹?,鍙繚鐣欑浉鍚岀殑鏁版嵁.灏辨槸涓や釜闆嗗悎鐨勪氦�?<br>
     * <code>
     * lst1 : [1,2,3] <br>
     * lst2 : [2,3]<br>
     * uninonList result is [2,3]</code>
     * 
     * @param <V>
     * @param lst1
     * @param lst2
     * @return
     */
    public static <V> List<V> unionList(List<V> lst1, List<V> lst2) {
        List<V> lst = new ArrayList<V>();

        if (lst1 == null && lst2 == null) {
            return lst;
        }
        if (lst1 == null) {
            return lst2;
        }
        if (lst2 == null) {
            return lst1;
        }

        for (V v : lst1) {
            if (lst2.contains(v)) {
                lst.add(v);
            }
        }
        return lst;
    }

    /**
     * 绉婚櫎lst2鍦╨st1涓殑鏁版嵁<br>
     * @param <V>
     * @param lst1
     * @param lst2
     * @return
     */
    public static <V>List<V> remove(List<V> lst1, List<V> lst2){
    	List<V> lst = new ArrayList<V>();
    	
        if (isEmpty(lst1) && isEmpty(lst2)) {
            return lst;
        }
        
        if (isEmpty(lst1)) {
            return lst1;
        }
        
        if (isEmpty(lst2)) {
            return lst1;
        }
        
        for (V v : lst1) {
            if (!lst2.contains(v)) {
                lst.add(v);
            }
        }
    	return lst;
    }
    
    public static <V> List<V> itToList(Iterator<V> it) {
        ArrayList<V> r = new ArrayList<V>();
        while (it.hasNext()) {
            r.add(it.next());
        }
        return r;

    }
    /**
     * 娓呯┖Collection
     * @param tempList
     */
    public static void clearCollection(Collection tempList){
    	if(isEmpty(tempList))return;
    	tempList.clear();
    	tempList=null;
    }
}
