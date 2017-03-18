package com.tiou.interfaces;

/**
 * Created by ronaldo on 06/03/2017.
 */
@FunctionalInterface
public interface Validator<T> {
    boolean valida(T t);
}
