package com.itsqmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itsqmet.entity.User;

@Controller
@RequestMapping
public class AuthController {
  @GetMapping
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String getMethodName(Model model) {
    model.addAttribute("user", new User());
    return "pages/registerUser";
  }

}
