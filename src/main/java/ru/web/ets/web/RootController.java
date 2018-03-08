package ru.web.ets.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.web.ets.web.forDocs.StudentController;

@Controller
public class RootController {


    @Autowired
    private StudentController controller;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/students")
    public String srudents(Model model)
    {
        model.addAttribute("students",
                controller.getAll());
        return "students";
    }
}
