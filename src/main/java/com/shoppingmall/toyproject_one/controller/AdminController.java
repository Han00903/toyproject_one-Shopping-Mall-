package com.shoppingmall.toyproject_one.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.shoppingmall.toyproject_one.DTO.adminDTO;
import com.shoppingmall.toyproject_one.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "toyproject_one/ad_main")
    public String ad_main(){
        return "/admin/ad_main";
    }

    @GetMapping(value = "toyproject_one/admin_main")
    public String admin_main(){
        return "/admin/admin_main";
    }

    @GetMapping("/toyproject_one/ad_signup") // 회원가입 페이지 출력 요청
    public String adminSignupForm(){

        return "/admin/ad_signup";
    }

    @GetMapping("toyproject_one/ad_login")
    public String adloginForm(){

        return "/admin/ad_login";
    }

    @PostMapping("/toyproject_one/ad_signup")
    public String admin_save(@Valid @ModelAttribute adminDTO adminDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 에러가 발생한 경우
            Map<String, String> validatorResult = adminService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/admin/ad_signup";
        } else {
            // 에러가 발생하지 않은 경우
            adminService.save(adminDTO);
            return "/admin/ad_login";
        }
    }

    @PostMapping("/toyproject_one/ad_login")
    public String ad_loginAction(@ModelAttribute adminDTO adminDTO, HttpSession session, Model model) {
        adminDTO loginResult = adminService.ad_login(adminDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginID", loginResult.getAdmin_id());
            return "/admin/admin_main";
        } else {
            // 로그인 실패
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다."); // 오류 메시지를 모델에 추가
            return "/admin/ad_login";
        }
    }
}
