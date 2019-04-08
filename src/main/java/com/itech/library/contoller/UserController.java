package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.dto.UserDto;
import com.itech.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("userKey")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"login", ""})
    private ModelAndView getLoginForm() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(Constant.View.User.LOGIN);

        return modelAndView;
    }

    @PostMapping("login")
    private ModelAndView login(@Valid UserDto userDto, BindingResult result,  HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.addObject("user", userDto);
            modelAndView.addObject("errors", result.getAllErrors());
            modelAndView.setViewName(Constant.View.User.LOGIN);
        } else if (userService.checkExistUser(userDto)) {
            session.invalidate();
            modelAndView.addObject("userKey", userDto.getLogin());
            modelAndView.setViewName(Constant.View.User.LOGIN);

        } else {
            modelAndView.addObject("message", "Invalid login or password");
            modelAndView.setViewName(Constant.View.User.LOGIN);
        }
        return modelAndView;
    }


}
