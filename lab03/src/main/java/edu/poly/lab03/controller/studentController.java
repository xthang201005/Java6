package edu.poly.lab03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import edu.poly.lab03.model.student;
@Controller
public class studentController {
    @GetMapping("/student/form")
    public String form (Model model) {
      student student = new student();
      student.setFullName("Nguyen Van A");
      student.setCountry("VN");
      student.setGender(true);
      model.addAttribute("sv", student);
    

        return "student/form";
    }
    @PostMapping("/student/save")
    public String save (@ModelAttribute("sv") student from ) {
        
        return "student/success";
    }
    
}
