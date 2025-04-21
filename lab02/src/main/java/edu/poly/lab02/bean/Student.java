package edu.poly.lab02.bean;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    String name;
    Integer age;
    String address;
    Double marks = 0.0;
    Contact contact;
    List<String> subjects;
}
