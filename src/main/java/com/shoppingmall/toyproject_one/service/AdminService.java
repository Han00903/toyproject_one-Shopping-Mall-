package com.shoppingmall.toyproject_one.service;

import com.shoppingmall.toyproject_one.DTO.adminDTO;
import com.shoppingmall.toyproject_one.entity.admin;
import com.shoppingmall.toyproject_one.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    @Autowired // 의존성 주입 (Repository 호출)
    private AdminRepository adminRepository;

    public void write(admin admin) {adminRepository.save(admin);}
    public void save(adminDTO adminDTO) {
        //1. dto -> entity 변환
        //2. repository의 save 메서드 호출
        admin admin = com.shoppingmall.toyproject_one.entity.admin.admin(adminDTO);
        adminRepository.save(admin);
        //repository의 save 메서드 호출 (조건 : entity 객체를 넘겨줘야 함)
    }

    public Map<String, String> validateHandling(Errors errors){
        Map<String, String> validatorResult = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
    public void signUp(adminDTO adminDTO) {
        // 회원 가입 비즈니스 로직 구현
    }

    public adminDTO ad_login(adminDTO adminDTO) {
        /*
         처리과정
         1. 회원이 입력한 아이디로 DB에서 조회함
         2. DB에서 조회한 비밀번호가 사용자가 입력한 비밀번호와 일치하는지 판단
        */
        Optional<admin> findById = adminRepository.findById(adminDTO.getAdmin_id());
        if (findById.isPresent()) {
            //조회 결과가 있다(해당 아이디를 가진 관리자 정보가 존재
            admin admin = findById.get();
            if (admin.getAdmin_pw().equals(adminDTO.getAdmin_pw())) {
                //비밀번호 일치
                // entity -> dto 변환 후 리턴
                adminDTO dto = com.shoppingmall.toyproject_one.DTO.adminDTO.adminDTO(admin);
                return dto;

            } else {
                // 비밀번호 불일치 (로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 아이디를 가진 관리자 정보가 부존재
            return null;
        }
    }
}