package edu.poly.lab01.streamAPI;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.poly.lab01.model.Student;

public class SteamAPI {
     static List<Student> list = List.of(
        new Student("John", false, 8.0),
        new Student("Marry", true, 9.0),
        new Student("Tom", false, 7.0),
        new Student("Jerry", false, 6.0),
        new Student("Alice", true, 5.0)
    );
    public static void main(String[] args) {
        //   demo1();
         //  demo2();
          // demo3();
         demo4();
    //   Stream<Integer> stream1 = Stream.of(1,2,3,4,5,6,7,8,9,10);
    //   stream1.forEach(n -> System.out.println(n));

    //   List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);
    //   list.stream().forEach(n -> System.out.println(n));

       }

    private static void demo1(){
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);
        list.forEach(n -> System.out.println(n));
    }

    private static void demo2(){
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);
        List<Double> result = list.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> Math.sqrt(n))
            .peek(d -> System.out.println(d))
            .collect(Collectors.toList());
    }
    private static void demo3(){
        List<Student> result = list.stream()
            .filter(sv -> sv.getMarks() >= 5)
            .peek(sv -> System.out.println(sv.getName().toUpperCase() + " - " + sv.getMarks())) 
            .collect(Collectors.toList());

    }

    private static void demo4(){
        double avg = list.stream()
            .mapToDouble(sv -> sv.getMarks())
            .average()
            .getAsDouble();
            System.out.println("Average: " + avg);

        double sum = list.stream()
            .mapToDouble(sv -> sv.getMarks())
            .sum();
            System.out.println("Sum: " + sum);

        double max = list.stream()
            .mapToDouble(sv -> sv.getMarks())
            .max()
            .getAsDouble();
            System.out.println("Max: " + max);
        
        double min = list.stream()  
            .mapToDouble(sv -> sv.getMarks())
            .min()
            .getAsDouble();
            System.out.println("Min: " + min);

        boolean all_passed = list.stream()
            .allMatch(sv -> sv.getMarks() >= 5);
            System.out.println("All passed: " + all_passed);
        
        Student min_sv = list.stream()
            .min((sv1, sv2) -> sv1.getMarks().compareTo(sv2.getMarks()))
            .get();
            System.out.println("Min student: " + min_sv.getName() + " - " + min_sv.getMarks());
    }

       

}
