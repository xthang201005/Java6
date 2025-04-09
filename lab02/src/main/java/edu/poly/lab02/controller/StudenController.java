package edu.poly.lab02.controller;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.poly.lab02.bean.Student;


@Controller
public class StudenController {
    static File fileJson = new File("D:\\Java6\\Git\\Java6App\\lab02\\src\\main\\resources\\static\\student.json");

    @RequestMapping("/student")
    public String student (Model model, @RequestParam("index") Optional<Integer> index)throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Student>> type = new TypeReference<List<Student>>(){};
        List<Student> students = mapper.readValue(fileJson, type);

        Student student = students.get(index.orElse(0));
        model.addAttribute("student", student);
        model.addAttribute("index", index.orElse(0)) ;
        return "student";
    }
}
