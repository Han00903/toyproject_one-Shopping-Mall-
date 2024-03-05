package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.userDTO;
import com.shoppingmall.toyproject_one.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "toyproject_one/main")
    public String main(){
        return "/user/main";
    }

    @GetMapping(value = "toyproject_one/user_main")
    public String User_main(){
        return "/user/user_main";
    }

    @GetMapping("/toyproject_one/signup")
    public String userSignupForm(){
        return "/user/signup";
    }

    @GetMapping("toyproject_one/login")
    public String loginForm(){
        return "/user/login";
    }

    @PostMapping("/toyproject_one/signup")
    public String save(@Valid  @ModelAttribute userDTO userDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/user/signup";
        } else {
            userService.save(userDTO);
            return "/user/login";
        }
    }
    private void openModal(String s) {
    }

    @PostMapping("/toyproject_one/login")
    public String loginAction(@ModelAttribute userDTO userDTO, HttpSession session, Model model) {
        userDTO loginResult = userService.login(userDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginID", loginResult.getUser_id());
            return "/user/user_main";
        } else {
            // 로그인 실패
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다."); // 오류 메시지를 모델에 추가
            return "/user/login";
        }
    }
}