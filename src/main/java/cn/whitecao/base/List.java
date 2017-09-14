/*
 * Copyright (c) 1997, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package cn.whitecao.base;

public interface List<E> {

    boolean add(E e);

    boolean addFirst(E e);

    Object[] toArray();

    E getFirst();

    E getLast();

    E get(int index);

    boolean remove(Object o);

    E remove(int index);

}
