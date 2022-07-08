package com.pxample.pemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController {


    @RequestMapping("/roleadd")
    public String numAdd () {
        int num = 10 / 0;   // ArithmeticException
        return  "roleadd";    // This clause will never be executed.
    }

}
