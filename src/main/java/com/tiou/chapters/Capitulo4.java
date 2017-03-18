package com.tiou.chapters;

import com.tiou.entity.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by ronaldo on 06/03/2017.
 */
public class Capitulo4 {
    public static void main(String... args){
        Usuario user1 = new Usuario("User 1", 150);
        Usuario user2 = new Usuario("User 2", 120);
        Usuario user3 = new Usuario("User 3", 190);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);

        Consumer<Usuario> mostraMessagem = usuario -> System.out.println("Antes de imprimir os nomes");

        Consumer<Usuario> imprimeNome = usuario -> System.out.println(usuario.getNome());

        Consumer<Usuario> imprimePontos = usuario -> System.out.println(usuario.getPontos());

        usuarios.forEach(mostraMessagem.andThen(imprimeNome.andThen(imprimePontos)));

        //##############

        Predicate<Usuario> predicate = new Predicate<Usuario>() {
            @Override
            public boolean test(Usuario usuario) {
                return usuario.getPontos() > 160;
            }
        };

        //usuarios.removeIf(predicate);
        usuarios.removeIf( usuario -> usuario.getPontos() > 160);
        usuarios.forEach(imprimeNome);
    }
}
