package ru.web.ets.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:index2";
    }

    @GetMapping("/index2")
    public String getIndex2() {
        return "index2";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
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

    @GetMapping("/practices")
    public String practices()
    {
        return "practices";
    }

    @GetMapping("/positions")
    public String positions()
    {
        return "positions";
    }


}
