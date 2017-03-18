package com.tiou.interfaces;

/**
 * Created by ronaldo on 07/03/2017.
 */
@FunctionalInterface
public interface TriFunction<F, S, T, R> {
    R apply(F f, S s, T t);
}
