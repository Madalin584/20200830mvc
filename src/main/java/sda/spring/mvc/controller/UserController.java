package sda.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sda.spring.mvc.model.User;
import sda.spring.mvc.model.dto.UserDTO;
import sda.spring.mvc.service.UserService;
import sda.spring.mvc.service.exception.UserNotFoundException;

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
    private final UserService userService;
    private static final String INDEX = "index";
    private static final String USERS = "users";

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listAll(Model model) {
        List<UserDTO> userDTOS = userService.findAll();
        model.addAttribute(USERS, userDTOS);
        return INDEX;
    }

    //validarea campurilor prin DTO
    //@ModelAttribute("user") face legatura dintre obiectul meu (DTO) si ceea ce returneaza formularul
    //de submit din HTML
    @PostMapping("/adduser")
    public String save(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) {
        //daca rezultatul are erori intorc aceeasi pagina
        if (bindingResult.hasErrors()) {
            return "user-add";
        }
        userService.save(userDTO);
        model.addAttribute(USERS, userService.findAll());
        return INDEX;
    }

    @GetMapping("/signup")
    public String getSignupPage(@ModelAttribute("user") User user) {
        return "user-add";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable("id") Long id) {
        UserDTO userDTO = userService.getById(id);
        if (userDTO == null) {
            throw new UserNotFoundException();
        }
        userService.remove(id);
        model.addAttribute(USERS, userService.findAll());
        return INDEX;
    }

    @GetMapping("/edit/{id}")
    public String updateUserPageView(Model model, @PathVariable Long id) {
        //aduce resursa si o afiseaza utilizatorului
        model.addAttribute("user", userService.getById(id));
        return "user-edit";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-edit";
        }
        userService.save(userDTO);
        model.addAttribute(USERS, userService.findAll());
        return "redirect:/";
    }
}
