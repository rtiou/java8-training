package com.tiou.chapters;

import com.tiou.entity.Usuario;
import com.tiou.helper.UsuarioHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by ronaldo on 07/03/2017.
 */
public class Capitulo7 {
    public static void main(String[] args) {
        List<Usuario> usuarios = UsuarioHelper.getUsuarioList();
        //sort(usuarios);
        //sortWithStream(usuarios);
        ///returnList(usuarios);
        //collector(usuarios);
        //map(usuarios);
        //average(usuarios);
        //optionalAverage(usuarios);
        maxPontos(usuarios);
    }

    public static void sort(List<Usuario> usuarios) {
        usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getPontos).reversed()));

        usuarios.subList(0, 10).forEach(Usuario::tornarModerador);
        usuarios.forEach(System.out::println);
    }

    public static void sortWithStream(List<Usuario> usuarios){
        //Stream<com.tiou.entity.Usuario> stream = usuarios.stream();
        //stream.filter(usuario -> usuario.getPontos() > 100);

        //Stream<com.tiou.entity.Usuario> stream = usuarios.stream().filter(usuario -> usuario.getPontos()>100);
        //stream.forEach(System.out::println);

        usuarios.stream().filter(usuario -> usuario.getPontos() > 100).forEach(usuario -> usuario.tornarModerador());
        usuarios.forEach(System.out::println);
    }

    public static void returnList(List<Usuario> usuarios){
        List<Usuario> maisQue100 = new ArrayList<>();
        usuarios.stream()
                .filter(usuario -> usuario.getPontos() > 100)
                //.forEach(usuario -> maisQue100.add(usuario));
                .forEach(maisQue100::add);
        maisQue100.forEach(System.out::println);
    }

    public static void collector(List<Usuario> usuarios) {
        List<Usuario> maisQue100 = usuarios.stream()
                .filter(usuario -> usuario.getPontos() > 100)
                .filter(usuario -> !usuario.isModerador())
                .collect(Collectors.toList());
        maisQue100.forEach(System.out::println);
    }

    public static void map(List<Usuario> usuarios){
        List<Integer> pontos = usuarios.stream()
                .map(Usuario::getPontos)
                .collect(Collectors.toList());
        pontos.forEach(System.out::println);
    }

    public static void average(List<Usuario> usuarios){
        double media = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .getAsDouble();
        System.out.println(media);
    }

    public static void optionalAverage(List<Usuario> usuarios) {
        double pontuacaoMedia = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElseThrow(IllegalArgumentException::new);
        System.out.println(pontuacaoMedia);
    }

    public static void maxPontos(List<Usuario> usuarios) {
        Optional<Usuario> max = usuarios
                .stream()
                .max(Comparator.comparing(Usuario::getPontos));
        System.out.println(max);

        Optional<String> maxName = usuarios
                .stream()
                .max(Comparator.comparingInt(Usuario::getPontos))
                .map(usuario -> usuario.getNome());
        System.out.println(maxName);
    }
}
