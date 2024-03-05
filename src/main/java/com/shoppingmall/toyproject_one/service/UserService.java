package com.shoppingmall.toyproject_one.service;

import com.shoppingmall.toyproject_one.DTO.userDTO;
import com.shoppingmall.toyproject_one.entity.user;
import com.shoppingmall.toyproject_one.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void write(user user) {
        userRepository.save(user);
    }

    public void save(userDTO userDTO) {
        user user = com.shoppingmall.toyproject_one.entity.user.user(userDTO);
        userRepository.save(user);
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public userDTO login(userDTO userDTO){
        /*
        * 회원이 입력한 아이디를 DB에서 조회를 함
        * DB에서 조회한비밀번호가 사용자가 입력한 비밀번호가 일치하는지 판단
        * */
        Optional<user> findById = userRepository.findById(userDTO.getUser_id());
        if (findById.isPresent()){
            //조회 결과가 있다(해당 아이디를 가진 회원 정보가 존재 비밀번호 일치여부는 아직 확인 x)
            user user = findById.get();
            if(user.getUser_pw().equals(userDTO.getUser_pw())){
                //비밀번호가 일치하는 경우
                // 엔티티 -> DTO 변환 후 리턴
                userDTO DTO = com.shoppingmall.toyproject_one.DTO.userDTO.userDTO(user);
                return DTO;

            }else{
                //비밀번호가 불일치하는 경우
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 아이디를 가진 회원 정보가 부존재)
            return null;
        }

    }
//
//    public boolean checkLogin(userDTO userDTO) {
//        user user = userRepository.findByUserID(userDTO.getUser_id());
//        return user != null && user.getUser_pw().equals(userDTO.getUser_pw());
//    }
}