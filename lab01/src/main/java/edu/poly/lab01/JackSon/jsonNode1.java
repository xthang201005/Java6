package edu.poly.lab01.JackSon;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonNode1 {

        public static void main(String[] args) throws Exception {
          //   demo1();
       //      demo2();
            // demo3();
          //  demo4();
        }

        private static void demo1() throws Exception{
            String json = "D:\\Java6\\Git\\Java6App\\lab01\\src\\main\\java\\edu\\poly\\lab01\\JackSon\\studenJson.json";
        
            ObjectMapper mapper = new ObjectMapper(); // create object mapper from jackson
            JsonNode student = mapper.readTree(new File(json)); // convert json to JsonNode

            System.out.println(student.get("name").asText());
            System.out.println(student.get("age").asInt());
            System.out.println(student.get("address").asText());
            System.out.println(student.get("marks").asDouble());
            System.out.println(student.get("contact").get("phone").asText());
            System.out.println(student.get("contact").get("email").asText());
            System.out.println(student.get("subjects").get(0).asText());
            System.out.println(student.get("subjects").get(1).asText());
            System.out.println(student.get("subjects").get(2).asText());

            
        }

        private static void demo2() throws Exception{
            String json = "D:\\Java6\\Git\\Java6App\\lab01\\src\\main\\java\\edu\\poly\\lab01\\JackSon\\studentsJson.json";
            ObjectMapper mapper = new ObjectMapper(); // create object mapper from jackson
            JsonNode students = mapper.readTree(new File(json)); // convert json to JsonNode
             // nhieu node doi qua iterator
            students.iterator().forEachRemaining(student ->{
                System.out.println(student.get("name").asText());
                System.out.println(student.get("age").asInt());
                System.out.println(student.get("address").asText());
                System.out.println(student.get("marks").asDouble());
                System.out.println(student.get("contact").get("phone").asText());
                System.out.println(student.get("contact").get("email").asText());
                System.out.println(student.get("subjects").get(0).asText());
                System.out.println(student.get("subjects").get(1).asText());
                System.out.println(student.get("subjects").get(2).asText());
                System.out.println("====================================");
            });
        }
}
