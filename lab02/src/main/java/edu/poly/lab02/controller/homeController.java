package edu.poly.lab02.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.poly.lab02.bean.Student;

import org.springframework.ui.Model;


@Controller
public class homeController {
    static File fileJson = new File("D:\\Java6\\Git\\Java6App\\lab02\\src\\main\\resources\\static\\student.json");
    @RequestMapping("/home/index")
    public String index(Model model) throws Exception {

        model.addAttribute("message", "Chào mừng bạn đến với trang web của chúng tôi");

        ObjectMapper  mapper = new ObjectMapper();
        // Chuyển JSON thành List<Student> vì file json chứa nhiều sinh viên mảng
        List<Student> students = Arrays.asList(mapper.readValue(fileJson, Student[].class));
        model.addAttribute("students", students);
        // System.out.println(students.getName());
        // System.out.println(students.getMarks());

        return "home/index";    
    }
    
}   
