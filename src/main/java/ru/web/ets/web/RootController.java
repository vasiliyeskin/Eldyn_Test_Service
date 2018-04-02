package ru.web.ets.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/students")
    public String srudents()
    {
        return "students";
    }

    @GetMapping("/organizations")
    public String organizations()
    {
        return "organizations";
    }

    @GetMapping("/scientificadviser")
    public String scientificadviser()
    {
        return "scientificadviser";
    }

    @GetMapping("/practiceDocs")
    public String practiceDocs()
    {
        return "practiceDocs";
    }

    @GetMapping("/users")
    public String users()
    {
        return "users";
    }


}
