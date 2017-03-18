package com.tiou.chapters;

import com.tiou.entity.Usuario;
import com.tiou.helper.UsuarioHelper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by ronaldo on 11/03/2017.
 */
public class Capitulo9 {
    private static long total = 0;
    public static void main(String... args) throws IOException {
        List<Usuario> usuarios = UsuarioHelper.getUsuarioList();
        //quatidadeLinhas();
        //getLinesPerFiles();
        //getLinesPerFiles2();
        //getLinesPerFiles3();
        //getUserName();
        //listOfUser();
        //splitBetweenModerator(usuarios);
        //splitBetweenModeratorWithSum(usuarios);
        //joinUserName(usuarios);
        //orderFilterParallel(com.tiou.entity.UsuarioHelper.getUsuarioList());
        //testParallel();
        sum();
    }

    public static void quatidadeLinhas() throws IOException {
        LongStream lines =
                Files.list(Paths.get("./src/main/java/com/tiou/casaDoCodigo"))
                        .filter(path -> path.toString().endsWith(".java"))
                        .mapToLong(path -> lines(path).count());
        //lines.forEach(System.out::println);

        List<Long> longLine =
                Files.list(Paths.get("./src/main/java/com/tiou/casaDoCodigo"))
                        .filter(path -> path.toString().endsWith(".java"))
                        .map(path -> lines(path).count())
                        .collect(Collectors.toList());
        longLine.forEach(System.out::println);
    }

    public static void getLinesPerFiles() throws IOException {
        Map<String, Long> linesPerFile = new HashMap<>();

        Files.list(Paths.get("./src/main/java/com/tiou/casaDoCodigo"))
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(
                        path -> linesPerFile.put(path.toString(), lines(path).count())
                );

        System.out.println(linesPerFile);
    }

    public static void getLinesPerFiles2() throws IOException{
        Map<Path, Long> lines =
                Files.list(Paths.get("./src/main/java/com/tiou/casaDoCodigo"))
                .filter(path -> path.toString().endsWith(".java"))
                .collect(Collectors.toMap(
                        path -> path,
                        path -> lines(path).count()));
        System.out.println(lines);
    }

    public static void getLinesPerFiles3() throws IOException{
        Map<Path, List<String>> content =
                Files.list(Paths.get("./src/main/java/com/tiou/casaDoCodigo"))
                .filter(path -> path.toString().endsWith(".java"))
                .collect(Collectors.toMap(
                        Function.identity(),
                        path -> lines(path).collect(Collectors.toList())));

        System.out.println(content);
    }

    public static void getUserName() {
        List<Usuario> usuarios = UsuarioHelper.getUsuarioList();
        Map<String, Usuario> nameToUser =
                usuarios.stream()
                .collect(Collectors.toMap(Usuario::getNome, Function.identity()));

        System.out.println(nameToUser);

    }

    public static void listOfUser() {
        List<Usuario> usuarios = UsuarioHelper.getUsuarioList();
        Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
        for(Usuario u : usuarios) {
            pontuacao
                    .computeIfAbsent(u.getPontos(), user -> new ArrayList<>())
                    .add(u);
        }
        //System.out.println(pontuacao);

        Map<Integer, List<Usuario>> pontuacao2 = usuarios
                .stream()
                .collect(Collectors.groupingBy(Usuario::getPontos));
        System.out.println(pontuacao2);
    }

    public static void splitBetweenModerator(List<Usuario> usuarios) {
        Map<Boolean, List<String>> moderadores = usuarios
                .stream()
                .collect(
                        Collectors.partitioningBy(
                                Usuario::isModerador,
                                Collectors.mapping(Usuario::getNome, Collectors.toList())));
        System.out.println(moderadores);
    }

    public static void splitBetweenModeratorWithSum(List<Usuario> usuarios) {
        Map<Boolean, Integer> pontuacaoPorTipo = usuarios
                .stream()
                .collect(
                        Collectors.partitioningBy(
                                Usuario::isModerador,
                                Collectors.summingInt(Usuario::getPontos)
                        )
                );
        System.out.println(pontuacaoPorTipo);
    }

    public static void joinUserName(List<Usuario> usuarios) {
        String nomes = usuarios
                .stream()
                .map(Usuario::getNome)
                .collect(Collectors.joining(", "));
        System.out.println(nomes);
    }

    public static void orderFilterParallel(List<Usuario> usuarios) {
        List<Usuario> filtradosOrdenados = usuarios
                .parallelStream()
                .filter(usuario -> usuario.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());
        Long end = System.currentTimeMillis();
    }

    public static void testParallel() {
        long init = System.currentTimeMillis();
        long sum =
                LongStream.range(0, 2_000_000_000)
                //.parallel()
                .filter(x -> x % 2 == 0)
                .sum();
        long end = System.currentTimeMillis();
        System.out.println(end - init);
        System.out.println(sum);//1167
    }

    public static void sum() {
        LongStream.range(0, 100_000_000)
                .parallel()
                .filter(x -> x % 2 == 0)
                .forEach(n -> total += n);
        System.out.println(total);
    }


    static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}