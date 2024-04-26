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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/toyproject_one/signup")
    public String userSignupForm() {

        return "/user/signup";
    }

    @GetMapping("toyproject_one/login")
    public String loginForm() {

        return "/user/login";
    }

    @PostMapping("/toyproject_one/signup")
    public String save(@Valid @ModelAttribute userDTO userDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/user/signup";
        } else {
            userService.save(userDTO);

            // 회원가입 성공
            model.addAttribute("message", "회원가입 되었습니다.");
            model.addAttribute("searchUrl", "/toyproject_one/login");
            return "message";
        }
    }

    private void openModal(String s) {
    }

    @PostMapping("/toyproject_one/login")
    public String loginAction(@ModelAttribute userDTO userDTO, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        userDTO loginResult = userService.login(userDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("userID", loginResult.getUserID()); // 세션에 userID 저장

            // 로그인 성공 후 user_main 페이지로 리다이렉트
            return "redirect:/toyproject_one/user_main";
        } else {
            // 로그인 실패
            model.addAttribute("message", "로그인 실패하였습니다. 다시입력해주세요.");
            model.addAttribute("searchUrl", "/toyproject_one/login");
            return "message";
        }
    }

    // ajax사용할때 return type앞에 ResponseBody 꼭 붙여야함
    @PostMapping("/toyproject_one/signup/id_check")
    public @ResponseBody String idcheck(@RequestParam("userID") String userID) {
        String checkResult = userService.idCheck(userID);
        return checkResult;
    }
}