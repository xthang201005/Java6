package edu.poly.lab01.JackSon;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.poly.lab01.JackSon.model.Contact;
import edu.poly.lab01.JackSon.model.Student2;

public class jsonMapp2 {
  static File fileJson = new File("D:\\Java6\\Git\\Java6App\\lab01\\src\\main\\java\\edu\\poly\\lab01\\JackSon\\studentsJson.json");
        
    public static void main(String[] args) throws Exception {
           //demo1(); // map mo ta doi tuong json thanh cac doi tuong java
         //  demo2(); // đọc từ map
        //   demo3(); // đọc từ class: List<Student2>
       //  demo4(); // TypeReference nên dùng khi chuyển đổi JSON thành List<Student2>
    //   demo5();
      //   demo6(); // tạo json từ map
         demo7(); // tạo json từ class
      }
      
      public static void demo1() throws Exception {
        // Dùng File trực tiếp thay vì chuỗi đường dẫn
    

        ObjectMapper mapper = new ObjectMapper(); // create object mapper from jackson
       // Chuyển JSON thành List<Map>
       List<Map<String, Object>> students = mapper.readValue(fileJson,mapper.getTypeFactory().constructCollectionType(List.class, Map.class)
    );
      // Duyệt qua danh sách sinh viên
      students.forEach(student -> {
        System.out.println("Name: " + student.get("name"));
        System.out.println("Age: " + student.get("age"));
        System.out.println("Address: " + student.get("address"));
        System.out.println("Marks: " + student.get("marks"));

        // Xử lý phần "contact"
        if (student.get("contact") instanceof Map) {
            Map<String, Object> contact = (Map<String, Object>) student.get("contact");
            System.out.println("  Phone: " + contact.get("phone"));
            System.out.println("  Email: " + contact.get("email"));
        }

        // Xử lý phần "subjects"
        if (student.get("subjects") instanceof List) {
            List<?> subjects = (List<?>) student.get("subjects");
            System.out.println("Subjects:");
            subjects.forEach(subject -> System.out.println("  - " + subject));
        }

        System.out.println("----------------------------");
    });

        
      }

      public static void demo2() throws Exception{
       // Dùng File trực tiếp thay vì chuỗi đường dẫn
        File fileJson = new File("D:\\Java6\\Git\\Java6App\\lab01\\src\\main\\java\\edu\\poly\\lab01\\JackSon\\studentsJson.json");

        ObjectMapper mapper = new ObjectMapper(); // create object mapper from jackson
       // Chuyển JSON thành List<Map>
       List<Map<String, Object>> students = mapper.readValue(fileJson,mapper.getTypeFactory().constructCollectionType(List.class, Map.class));
      // Duyệt qua danh sách sinh viên
      students.forEach(student -> {
        student.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
      });
      }
      // Dùng TypeReference để ánh xạ thành List<Student2> 
    
      public static void demo3()throws Exception{
        ObjectMapper mapper = new ObjectMapper(); // create object mapper from jackson
    
    // Dùng TypeReference để ánh xạ thành List<Student2>
    List<Student2> students = mapper.readValue(fileJson,new com.fasterxml.jackson.core.type.TypeReference<List<Student2>>() {});
    
    // Duyệt qua danh sách sinh viên và in thông tin ra màn hình
    students.forEach(student -> {
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Address: " + student.getAddress());
        System.out.println("Marks: " + student.getMarks());

        // In thông tin contact
        if (student.getContact() != null) {
            System.out.println("  Phone: " + student.getContact().getPhone());
            System.out.println("  Email: " + student.getContact().getEmail());
        }

        // In danh sách subjects
        if (student.getSubjects() != null) {
            System.out.println("Subjects:");
            student.getSubjects().forEach(subject -> System.out.println("  - " + subject));
        }

        System.out.println("----------------------------");
    });
  }
    public static void demo4()  throws Exception{
        TypeReference<List<Student2>> type = new TypeReference<List<Student2>>() {};
        ObjectMapper mapper = new ObjectMapper(); // create object mapper from jackson
        List<Student2> students = mapper.readValue(fileJson, type);
        students.forEach(student -> {
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Marks: " + student.getMarks());
    
            // In thông tin contact
            if (student.getContact() != null) {
                System.out.println("  Phone: " + student.getContact().getPhone());
                System.out.println("  Email: " + student.getContact().getEmail());
            }
    
            // In danh sách subjects
            if (student.getSubjects() != null) {
                System.out.println("Subjects:");
                student.getSubjects().forEach(subject -> System.out.println("  - " + subject));
            }
    
            System.out.println("----------------------------");
        });
  }
  //  dùng map tạo json
    private static void demo6() throws Exception{
      Map<String, Object> contact = new HashMap<String  , Object>();
      contact.put("phone", "0987654321");
      contact.put("email", "demoMap@gmail.com");

      List<String> subjects = List.of("Math", "English", "Java");

      Map<String, Object> student = new HashMap<String, Object>();
      student.put("name", "Alice");
      student.put("age", 20);
      student.put("address", "Hanoi");
      student.put("marks", 8.0);
      student.put("contact", contact);
      student.put("subjects", subjects);

      ObjectMapper mapper = new ObjectMapper();
      // chuyển về chuỗi json
      String json = mapper.writeValueAsString(student);
      System.out.println(json);
      // chuyển về file json
      mapper.writeValue(System.out, student);
      // tạo file json
      mapper.writeValue(new File("D:\\Java6\\Git\\Java6App\\lab01\\src\\main\\java\\edu\\poly\\lab01\\JackSon\\studentMap.json"), student);
    }

    public static void demo7()throws Exception {
      Contact contact = new Contact("0987654321", "demo@gmai.com", "Hanoi");
      List<String> subjects = List.of("Math", "English", "Java");
      Student2 student = new Student2("demo2 ", true, 20, "Hanoi", 8.0, contact, subjects);

      ObjectMapper mapper = new ObjectMapper();
      // chuyển về chuỗi json
      String json = mapper.writeValueAsString(student);
      System.out.println(json);
      // chuyển về file json
      mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, student);
      // tạo file json
      mapper.writeValue(new File("D:\\Java6\\Git\\Java6App\\lab01\\src\\main\\java\\edu\\poly\\lab01\\JackSon\\studentClass.json"), student);
    }
} 
