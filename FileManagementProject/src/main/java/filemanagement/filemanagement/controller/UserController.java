package filemanagement.filemanagement.controller;

import filemanagement.filemanagement.domain.User;
import filemanagement.filemanagement.domain.UserForm;
import filemanagement.filemanagement.domain.UserRole;
import filemanagement.filemanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
public class UserController {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService , PasswordEncoder passwordEncoder){

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping({"login"})
    public String loginForm(){
        return "login";
    }

//    @RequestMapping(value = {"/","login"}, method = RequestMethod.POST)
//    public String login(@RequestParam("email")String email, @RequestParam("password") String password){
//
//        return "login";
//    }

    @RequestMapping("signup")
    public String signupForm(@ModelAttribute("userForm") UserForm userForm){
        return "register";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signupUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

//        user.setRole(userForm.getRole());
        user.setRole(UserRole.USER);

        // save user to db.
        userService.saveUser(user);

        // redirect
        ra.addFlashAttribute("success", true);
        return "redirect:/signup";
    }

    @GetMapping(value = {"/","/home"})
    public String home(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("userId", user.getId());
        model.addAttribute("files", user.getFiles().values());
        return "home";
    }

    @PostMapping("/failed_login")
    public String handleFailedLogin(RedirectAttributes ra) {
        ra.addFlashAttribute("failed_login",true);
        return "redirect:/login";
    }

    @GetMapping("/failed_login")
    public String handleFailedLogin1233(RedirectAttributes ra) {
        ra.addFlashAttribute("failed_login",true);
        return "redirect:/login";
    }

}
