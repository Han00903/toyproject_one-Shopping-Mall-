package com.shoppingmall.toyproject_one.service;

import com.shoppingmall.toyproject_one.DTO.itemDTO;
import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    @Autowired // 의존성 주입 (Repository 호출)
    private ItemRepository itemRepository;

    // 글 작성
    public void write(item item, MultipartFile file) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, "fileName");

        file.transferTo(saveFile);

        itemRepository.save(item);
    }

    public void save(itemDTO itemDTO) {
        //1. dto -> entity 변환
        //2. repository의 save 메서드 호출
        item item = com.shoppingmall.toyproject_one.entity.item.item(itemDTO);
        itemRepository.save(item);
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
    public void item(itemDTO itemDTO) {
        // 회원 가입 비즈니스 로직 구현
    }

    //게시글 리스트 처리
    public List<item> itemList(){
        return itemRepository.findAll();
    }

    //특정 게시글 불러오기
    public item itemView(String item_id) {
        return itemRepository.findById(item_id).get();
    }
}
