package com.example.lab6.controllers;

import com.example.lab6.dao.UserDao;
import com.example.lab6.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

//    @PostMapping("/register")
//    public String registerPagePOST(@ModelAttribute User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        dao.save(user);
//        return "redirect:/login";
//    }

    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult binding) {
        if (binding.hasErrors()) {
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "profile";
    }

    @GetMapping("/users")
    public String users(Model m) {
        m.addAttribute("users", dao.findAll());
        return "users";
    }

    @GetMapping("/edit")
    public String editPage(Model m, Principal principal) {
        User user = dao.findByLogin(principal.getName());
        user.setPassword("");
        m.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String editPagePOST(@ModelAttribute User user, Principal p) throws Exception {
        String newPassword = user.getPassword();
        if(newPassword.isBlank()) {
            String existingPassword = dao.findByLogin(p.getName()).getPassword();
            user.setPassword(existingPassword);
        } else {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        User existingUser = dao.findByLogin(user.getLogin());
        if(existingUser != null && p.getName().equals(existingUser.getName()))
            throw new Exception("Login " + user.getLogin() + "is taken.");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        dao.save(user);
        return "redirect:/profile";
    }

    @GetMapping("/delete")
    public String editPagePOST(Principal principal) {
        User user = dao.findByLogin(principal.getName());
        dao.delete(user);
        return "redirect:/logout";
    }
}
