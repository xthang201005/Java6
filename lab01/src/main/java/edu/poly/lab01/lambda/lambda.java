package edu.poly.lab01.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.poly.lab01.model.Student;
  
public class lambda {
    static List<Student> list = List.of(
        new Student("John", false, 8.0),
        new Student("Marry", true, 9.0),
        new Student("Tom", false, 7.0),
        new Student("Jerry", false, 6.0),
        new Student("Alice", true, 5.0)
    );
  
    public static void main(String[] args) {
     //   demo1();
    //    demo2();
      //  demo3();
      demo4();
    }
    

    private static void demo1(){
            List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);
            list.forEach(n -> System.out.println(n));
    }

    private static void demo2(){
        List<Student> list = List.of(
            new Student("John", false, 8.0),
            new Student("Marry", true, 9.0),
            new Student("Tom", false, 7.0),
            new Student("Jerry", false, 6.0),
            new Student("Alice", true, 5.0)
        );
        list.forEach(sv -> {
            System.out.println("Name: " + sv.getName());
            System.out.println("Mark: " + sv.getMarks());
        });
    }

   private static void demo3() {
    // Tạo một danh sách mới có thể chỉnh sửa
    List<Student> mutableList = new ArrayList<>(list);

    Collections.sort(mutableList, (sv1, sv2) -> 
        -sv1.getMarks().compareTo(sv2.getMarks())); // Sắp xếp giảm dần
    
    mutableList.forEach(sv -> {
        System.out.println("Name: " + sv.getName());
        System.out.println("Mark: " + sv.getMarks());
    });

    
}
    // Demo lambda với interface 
    // 1  đối số kh cần dùng () {}
    public static void demo4() {
        MyFunction f = (x) -> {System.out.println(x);};
        f.m1(100);
    }
    @FunctionalInterface
    interface MyFunction {
        void m1(int x);
        default void m2(){};
        public static void m3(){};

    }
}
