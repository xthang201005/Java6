package edu.poly.lab02.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import edu.poly.lab02.bean.Student;

@Controller
public class Studen2Controller {
    @RequestMapping("/student2/list")
   public String list(Model model, @RequestParam("index") Optional<Integer> index) throws Exception{
    File file = new ClassPathResource("/static/studentsJson.json").getFile();
    ObjectMapper mapper = new ObjectMapper();
    TypeReference <List<Student>> type = new TypeReference<List<Student>> () {};
    List<Student> list = mapper.readValue(file, type);

    model.addAttribute("sv", list.get(index.orElse(0)));
    model.addAttribute("dssv", list);
    return "student2/list";
     
}
}
