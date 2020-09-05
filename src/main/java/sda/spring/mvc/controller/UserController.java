package sda.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sda.spring.mvc.model.User;
import sda.spring.mvc.model.dto.UserDTO;
import sda.spring.mvc.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Paths:
 * "/"
 * "/signup"
 * "/adduser"
 * "/edit/{id}" -- GET
 * "/update/{id}" -- POST
 * "/delete/{id}"
 */
@Controller
public class UserController {
    private UserService userService;
    private final String index = "index";

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listAll(Model model) {
        List<UserDTO> userDTOS = userService.findAll();
        model.addAttribute("users", userDTOS);
        return index;
    }

    @PostMapping("/adduser")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        //daca rezultatul are erori intorc aceeasi pagina
        if (bindingResult.hasErrors()) {
            return "user-add";
        }
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return index;
    }

    @GetMapping("/signup")
    public String getSignupPage(User user) {
        return "user-add";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable("id") Long id) {
        userService.remove(id);
        model.addAttribute("users", userService.findAll());
        return index;
    }

    @GetMapping("/edit/{id}")
    public String updateUserPageView(Model model, @PathVariable Long id) {
        //aduce resursa si o afiseaza utilizatorului
        model.addAttribute("user", userService.getById(id));
        return "user-edit";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid User user, @PathVariable Long id, BindingResult result, Model model) {
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/";
    }
}
