package com.tiou.chapters;

import com.tiou.interfaces.TriFunction;
import com.tiou.entity.Usuario;
import com.tiou.helper.UsuarioHelper;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by ronaldo on 06/03/2017.
 */
public class Capitulo6 {
    public static void main(String... args){
        List<Usuario> usuarios = UsuarioHelper.getUsuarioList();
        //sortExample(usuarios);
        //methodReference();
        //methodWithArguments(usuarios);
        constructorRef();
    }

    public static void sortExample(List<Usuario> usuarios) {
        usuarios.forEach(usuario -> System.out.println(usuario.isModerador()));
        usuarios.forEach(Usuario::tornarModerador);
        usuarios.forEach(usuario -> System.out.println(usuario.isModerador()));

        usuarios.forEach(usuario -> System.out.println(usuario.getNome()));
        //usuarios.sort(Comparator.comparing(u -> u.getNome()));
        //usuarios.sort(Comparator.comparing(com.tiou.entity.Usuario::getNome));
        Function<Usuario, String> byName = Usuario::getNome;
        //usuarios.sort(Comparator.comparing(byName));
        //usuarios.forEach(usuario -> System.out.println(usuario.getNome()));

        usuarios.forEach(usuario -> System.out.println(usuario.getPontos()));
        //usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));
        usuarios.sort(Comparator.comparingInt(Usuario::getPontos));
        usuarios.forEach(usuario -> System.out.println(usuario.getPontos()));

        usuarios.sort(Comparator.nullsLast(Comparator.comparingInt(Usuario::getPontos).reversed().thenComparing(Usuario::getNome)));
        usuarios.forEach(usuario -> System.out.println(usuario.getNome()));
    }

    public static void methodReference() {
        Usuario user1 = new Usuario("User 1 ", 50);
        System.out.println(user1.isModerador());
        Runnable bloco = user1::tornarModerador;
        //bloco.run();
        System.out.println(user1.isModerador());

        Consumer<Usuario> consumer = Usuario::tornarModerador;
        consumer.accept(user1);
        System.out.println(user1.isModerador());
    }

    public static void methodWithArguments(List<Usuario> usuarios){
        usuarios.forEach(System.out::println);
        //usuarios.forEach(usuario -> System.out.println(usuario));
    }

    public static void constructorRef(){
        Supplier<Usuario> criadorDeUsuario = Usuario::new;
        Usuario user1 = criadorDeUsuario.get();

        Function<String, Usuario> criadorDeUsuario2 = Usuario::new;
        Usuario user2 = criadorDeUsuario2.apply("User 2");
        Usuario user3 = criadorDeUsuario2.apply("User 3");

        BiFunction<String, Integer, Usuario> criadorDeUsuario3 = Usuario::new;
        Usuario user4 = criadorDeUsuario3.apply("", 10);

        TriFunction<String, Integer, Boolean, Usuario> criadorDeUsuario4 = Usuario::new;
        Usuario user5 = criadorDeUsuario4.apply("User 5", 666, Boolean.FALSE);
        System.out.println(user5);
    }
}
