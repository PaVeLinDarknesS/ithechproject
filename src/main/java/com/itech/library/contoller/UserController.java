package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.dto.UserDto;
import com.itech.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("userKey")
public class UserController {

    @Autowired
    private UserService userService;

    //@GetMapping({"login", ""})
    private String login() {
        return Constant.View.User.LOGIN;
    }

    //@PostMapping("login")
    private String login(@Valid UserDto userDto, BindingResult result, HttpSession session, Model model) {
        String view;

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            model.addAttribute("errors", result.getAllErrors());
            view = Constant.View.User.LOGIN;
        } else if (userService.checkExistUser(userDto)) {
            session.invalidate();
            model.addAttribute("userKey", userDto.getLogin());
            view = Constant.View.User.LOGIN;

        } else {
            model.addAttribute("message", "Invalid login or password");
            view = Constant.View.User.LOGIN;
        }
        return view;
    }


    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is welcome page!");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page - Admin Page!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/dba**", method = RequestMethod.GET)
    public ModelAndView dbaPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page - Database Page!");
        model.setViewName("admin");

        return model;

    }


}
