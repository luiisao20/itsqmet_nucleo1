package com.itsqmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itsqmet.entity.User;
import com.itsqmet.service.UserService;

import jakarta.validation.Valid;
import lombok.Data;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public String registerUser(@Valid @ModelAttribute("user") User user,
      BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      return "pages/registerUser";
    }
    if (user.getId() != null) {
      userService.updateUser(user.getId(), user);
      return "redirect:/users";
    }
    userService.registerUser(user);
    return "redirect:/login";
  }

}
