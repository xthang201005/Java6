package edu.poly.lab01.JackSon.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {
    String name;
    Boolean gender;
    Integer age;
    String address;
    Double marks = 0.0;
    Contact contact;
    List<String> subjects;
}
