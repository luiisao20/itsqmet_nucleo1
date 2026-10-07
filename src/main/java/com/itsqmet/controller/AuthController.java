package com.itsqmet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itsqmet.entity.User;

@Controller
@RequestMapping
public class AuthController {
  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String getMethodName(Model model) {
    model.addAttribute("user", new User());
    return "pages/registerUser";
  }

  @GetMapping("/post-login")
  public String redirectByRole(Authentication auth) {
    org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth
        .getPrincipal();
    String role = user.getAuthorities().stream()
        .map(grantedAuthority -> grantedAuthority.getAuthority())
        .findFirst()
        .orElse("");

    return switch (role) {
      case "ROLE_ADMIN" -> "redirect:/";
      default -> "redirect:/";
    };
  }

}
