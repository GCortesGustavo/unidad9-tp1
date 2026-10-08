package unidad9.tp1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Juan", 8.5, "Programación"),
                new Alumno("María", 9.2, "Programación"),
                new Alumno("Pedro", 6.5, "Base de Datos"),
                new Alumno("Lucía", 7.8, "Base de Datos"),
                new Alumno("Carlos", 9.5, "Programación"),
                new Alumno("Ana", 5.9, "Redes"),
                new Alumno("Sofía", 8.9, "Redes"),
                new Alumno("Martín", 7.2, "Redes")
        );

        // 1. Nombres de alumnos aprobados, en mayúsculas y ordenados
        List<String> aprobados = alumnos.stream()
                .filter(alumno -> alumno.getNota() >= 7)
                .map(alumno -> alumno.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("1. Alumnos aprobados:");
        aprobados.forEach(System.out::println);

        // 2. Promedio general de notas
        double promedio = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0);

        System.out.println("\n2. Promedio general: " + promedio);

        // 3. Agrupar alumnos por curso
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));

        System.out.println("\n3. Alumnos agrupados por curso:");

        alumnosPorCurso.forEach((curso, lista) -> {
            System.out.println("\nCurso: " + curso);

            lista.forEach(alumno ->
                    System.out.println("  " + alumno.getNombre()
                            + " - Nota: " + alumno.getNota())
            );
        });

        // 4. Obtener los 3 mejores promedios
        List<Alumno> mejores = alumnos.stream()
                .sorted((a1, a2) -> Double.compare(a2.getNota(), a1.getNota()))
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\n4. Los 3 mejores promedios:");

        mejores.forEach(alumno ->
                System.out.println(alumno.getNombre()
                        + " - " + alumno.getNota())
        );
    }
}