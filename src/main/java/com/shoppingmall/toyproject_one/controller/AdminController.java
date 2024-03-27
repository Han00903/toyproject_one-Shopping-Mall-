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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 충돌일어나서 ad_main은 ItemController로 감
//    @GetMapping(value = "toyproject_one/ad_main")
//    public String ad_main() {
//        return "/admin/ad_main";
//    }


    // 충돌일어나서 admin_main은 ItemController로 감
//    @GetMapping(value = "toyproject_one/admin_main")
//    public String admin_main() {
//        return "/admin/admin_main";
//    }

    @GetMapping("/toyproject_one/ad_signup") // 회원가입 페이지 출력 요청
    public String adminSignupForm() {

        return "/admin/ad_signup";
    }

    @GetMapping("toyproject_one/ad_login")
    public String adloginForm() {

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

            model.addAttribute("message", "다시 입력해주세요.");
            model.addAttribute("searchUrl", "/toyproject_one/ad_signup");

            return "message"; // 회원가입이 완료된 경우 메시지 페이지를 보여줌

        } else {
            // 에러가 발생하지 않은 경우
            adminService.save(adminDTO);
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            model.addAttribute("searchUrl", "/toyproject_one/ad_login");

            return "message"; // 회원가입이 완료된 경우 메시지 페이지를 보여줌
        }
    }

    @PostMapping("/toyproject_one/ad_login")
    public String ad_loginAction(@ModelAttribute adminDTO adminDTO, HttpSession session, Model model) {
        adminDTO loginResult = adminService.ad_login(adminDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginID", loginResult.getAdmin_id());

            model.addAttribute("message", "로그인이 완료되었습니다.");
            model.addAttribute("searchUrl", "/toyproject_one/admin_main");

            return "message";
        } else {
            // 로그인 실패

            model.addAttribute("message", "로그인 실패하였습니다.");
            model.addAttribute("searchUrl", "/toyproject_one/ad_login");
            return "message";
        }
    }

    @PostMapping("/toyproject_one/ad_signup/ad_id_check")
    public @ResponseBody String ad_idcheck (@RequestParam("admin_id") String admin_id){
        String ad_checkResult = adminService.ad_idCheck(admin_id);
        return ad_checkResult;
    }
}
