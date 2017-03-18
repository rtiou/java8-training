package com.tiou.chapters;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ronaldo on 14/03/2017.
 */
public class Capitulo10 {
    public static void main(String[] args) {
        //mesQueVem();
        //setDate();
        //compareDate();
        //compareTimeZone();
        //getDayOfMonth();
        //enumDate();
        //localeDate();
        //formatDate();
        //parseDate();
        //invalidDate();
        //diferencBetweenCalendar();
        durationTime();
    }

    public static void mesQueVem(){
        Calendar mesQueVem = Calendar.getInstance();
        mesQueVem.add(Calendar.MONTH, 1);
        System.out.println(mesQueVem);

        LocalDate mesQueVemNew = LocalDate.now().plusMonths(1);
        System.out.println();
        System.out.println(mesQueVemNew);

        LocalTime hora = LocalTime.now();
        System.out.println(hora);

        LocalDateTime agora = LocalDateTime.now();
        System.out.println(agora);

        LocalDateTime hojeMeioDia = LocalDate.now().atTime(12,0);
        System.out.println(hojeMeioDia);
    }

    public static void setDate() {
        LocalTime agora = LocalTime.now();
        LocalDate hoje = LocalDate.now();
        LocalDateTime dataEhora = hoje.atTime(agora);

        System.out.println(dataEhora);

        ZonedDateTime dataComHoraeTimeZone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
        System.out.println(dataComHoraeTimeZone);

        LocalDateTime semTimeZone = dataComHoraeTimeZone.toLocalDateTime();
        System.out.println(semTimeZone);

        LocalDate date = LocalDate.of(2040, 12, 31);
        System.out.println(date);

        LocalDateTime dateTime = LocalDateTime.of(2016, 3, 14, 13, 45);
        System.out.println(dateTime);

        LocalDate dataDoPassado = LocalDate.now().withYear(1984);
        System.out.println(dataDoPassado.getYear());
    }

    public static void compareDate() {
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = LocalDate.now().plusDays(1);

        System.out.println(hoje.isBefore(amanha));
        System.out.println(hoje.isAfter(amanha));
        System.out.println(hoje.isEqual(amanha));
    }

    public static void compareTimeZone() {
        ZonedDateTime amsterdam = ZonedDateTime.of(2016, 5, 2, 10, 30, 0, 0, ZoneId.of("Europe/Amsterdam"));
        amsterdam = amsterdam.plusHours(5);
        ZonedDateTime saoPaulo = ZonedDateTime.of(2016, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
        System.out.println(amsterdam);
        System.out.println(saoPaulo);
        System.out.println(amsterdam.isEqual(saoPaulo));
    }

    public static void getDayOfMonth() {
        System.out.println("hoje é dia: " + MonthDay.now().getDayOfMonth());
        YearMonth ym = YearMonth.from(YearMonth.now());
        System.out.println(ym.getMonth() + " " + ym.getYear());
    }

    public static void enumDate() {
        System.out.println(LocalDate.of(2016, 3, 14));
        System.out.println(LocalDate.of(2016, Month.MARCH, 14));

        System.out.println(Month.DECEMBER.firstMonthOfQuarter());
        System.out.println(Month.DECEMBER.plus(2));
        System.out.println(Month.DECEMBER.minus(99));

    }

    public static void localeDate() {
        Locale pt = new Locale("zh");//pt
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT_STANDALONE, pt));

        System.out.println(DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, pt));
    }

    public static void formatDate() {
        LocalDateTime agora = LocalDateTime.now();
        String resultado = agora.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(resultado);
        System.out.println(agora);

        System.out.println(agora.format(DateTimeFormatter.ofPattern("dd\\M\\yy")));
    }

    public static void parseDate() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado = agora.format(formatador);
        System.out.println(resultado);
        LocalDate agoraEmData = LocalDate.parse(resultado, formatador);
        System.out.println(agoraEmData);
    }

    public static void invalidDate() {
        Calendar instante = Calendar.getInstance();
        instante.set(2016, Calendar.FEBRUARY, 31);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(instante.getTime()));

        //java.time.DateTimeException: Invalid date 'FEBRUARY 30'
        //System.out.println(LocalDate.of(2016, Month.FEBRUARY, 30));

        //java.time.DateTimeException: Invalid value for HourOfDay (valid values 0 - 23): 25
        System.out.println(LocalDate.now().atTime(25,0));
    }

    public static void diferencBetweenCalendar() {
        Calendar agora = Calendar.getInstance();

        Calendar outraData = Calendar.getInstance();
        outraData.set(1989, Calendar.JANUARY, 25);

        long diferenca = agora.getTimeInMillis() - outraData.getTimeInMillis();

        long milissegundosDeUmDia = 1000 * 60 * 60* 24;
        long dias = diferenca / milissegundosDeUmDia;

        System.out.println(dias);

        /// new soluction
        LocalDate agora2 = LocalDate.now();
        LocalDate outraData2 = LocalDate.of(2989, Month.MARCH, 15);
        long dias2 = ChronoUnit.DAYS.between(outraData2, agora2);
        System.out.println(dias2);
        System.out.println(ChronoUnit.MONTHS.between(outraData2, agora2));

        //second new soluction
        Period periodo = Period.between(outraData2, agora2);
        System.out.printf("%s dias, %s meses, e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());
        if (periodo.isNegative()) {
            periodo = periodo.negated();
            System.out.printf("\n%s dias, %s meses, e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());
        }
    }

    public static void durationTime() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime daquiUmaHora = LocalDateTime.now().plusWeeks(1);

        Duration duration = Duration.between(agora, daquiUmaHora);

        if (duration.isNegative()){
            duration = duration.negated();
        }

        System.out.printf("%s horas, %s minutos e %s segundos", duration.toHours(), duration.toMinutes(), duration.getSeconds());
    }
}
