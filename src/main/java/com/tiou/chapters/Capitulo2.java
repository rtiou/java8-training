package com.tiou.chapters;

import com.tiou.entity.Mostrador;
import com.tiou.entity.Usuario;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by ronaldo on 06/03/2017.
 */
public class Capitulo2 {
    public static void main(String... args){
        Usuario user1 = new Usuario("Usuario 1", 150);
        Usuario user2 = new Usuario("Usuario 2", 120);
        Usuario user3 = new Usuario("Usuario 3", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        //Solution 1
        System.out.println("Solution 01");
        for (Usuario u : usuarios) {
            System.out.println(u.getNome());
        }

        //Solution 2
        System.out.println("\nSolution 02");
        usuarios.forEach(new Mostrador());

        //Solution 3
        System.out.println("\nSolution 03");
        Consumer<Usuario> mostrador = new Consumer<Usuario>() {
            @Override
            public void accept(Usuario usuario) {
                System.out.println(usuario.getNome());
            }
        };

        usuarios.forEach(mostrador);

        //Solution 4
        System.out.println("\nSolution 04");
        usuarios.forEach(new Consumer<Usuario>() {
            @Override
            public void accept(Usuario usuario) {
                System.out.println(usuario.getNome());
            }
        });

        //Solution 5 Lambda
        System.out.println("\nSolution 05");
        Consumer<Usuario> mostradorLambda = (usuario) -> System.out.println(usuario.getNome());
        usuarios.forEach(mostradorLambda);

        //Solution 6 Lambda
        System.out.println("\nSolution 06");
        usuarios.forEach(usuario -> System.out.println(usuario.getNome()));

        usuarios.forEach(usuario -> System.out.println(usuario.toString()));
        usuarios.forEach(usuario -> usuario.tornarModerador());
        usuarios.forEach(usuario -> System.out.println(usuario.toString()));
    }
}
