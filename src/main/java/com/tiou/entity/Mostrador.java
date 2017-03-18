package com.tiou.entity;

import java.util.function.Consumer;

/**
 * Created by ronaldo on 06/03/2017.
 */
public class Mostrador implements Consumer<Usuario> {
    @Override
    public void accept(Usuario usuario) {
        System.out.println(usuario.getNome());
    }
}
