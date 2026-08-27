package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Список всех юзеров
    @GetMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    // Форма добавления/редактирования (если id не передан, то добавление)
    @GetMapping("/user-form")
    public String showUserForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("user", userService.getUserById(id));
        } else {
            model.addAttribute("user", new User());
        }
        return "user-form";
    }

    // Сохранение или обновление юзера
    @PostMapping("/save-user")
    public String saveUser(User user) {
        if (user.getId() == null) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    // Удаление юзера
    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}