package com.pxample.pemo.controller;

import com.pxample.pemo.pojo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/pemo")
    public String showPemo(Model model) {
        model.addAttribute("message", "Hello Thymeleaf");

        Double score = 70.3;
        model.addAttribute("score", score);
        model.addAttribute("GPA", convertGPA(score));

        List<User> usrlst = new ArrayList<>();
        usrlst.add(new User(1, "Tom", "123", 30, 30.1, "tom@co.com"));
       // usrlst.add(new User(2, "Jerry", "123", 29, 70.8, "jerry@co.com"));
        //usrlst.add(new User(3, "Nancy", "123", 27, 90.5, "nancy@co.com"));
        model.addAttribute("users", usrlst);

        return "pemo";
    }

    @RequestMapping("/pemoscope")
    public String demo3(HttpServletRequest request, Model model) {
        // Request
        request.setAttribute("myrequestname", "request data");
        // Session
        request.getSession().setAttribute("mysessionname", "session data");
        // Application
        request.getSession().getServletContext().setAttribute("myapplicationname", "application data");
        return "pemoscope";
    }

    private String convertGPA(Double score) {

        if (Double.compare(score, 90)>=0) {
            return "A";
        } else if (Double.compare(score, 90)<0 && Double.compare(score, 80)>=0) {
            return "B";
        } else if (Double.compare(score, 80)<0 && Double.compare(score, 70)>=0) {
            return "C";
        } else if (Double.compare(score, 70)<0 && Double.compare(score, 60)>=0) {
            return "D";
        } else {
            return "F";
        }
    }

    @RequestMapping("/add")
    public String toAdd(User user) {
        return "add";
    }

    @RequestMapping("/addUser")
    public String add(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        System.out.println(("Save user =" +user));
        return "success";
    }

    /*
     * Exception *******************
     */
    @RequestMapping("/updateuser")
    public String update() {
        String name = null;
        name = name.toLowerCase();  // Null Pointer Exception
        return "updateuser"; // this clause will never be executed.
    }

}
