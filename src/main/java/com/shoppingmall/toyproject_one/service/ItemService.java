package com.shoppingmall.toyproject_one.service;

import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void write(item item) {
        itemRepository.save(item);
    }

    // 글 작성 처리
    public void write(item item, MultipartFile file) throws Exception {

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String imgID = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, imgID);
        file.transferTo(saveFile);

        item.setImgID(imgID);
        item.setItem_img_filepath("/files/" + imgID);

        itemRepository.save(item);
    }

    // 게시글 리스트 처리
    public Page<item> itemList(Pageable pageable) {

        return itemRepository.findAll(pageable);
    }

    // 게시글 검색 기능 추가
    public Page<item> itemSearchList(String searchKeyword, Pageable pageable) {
        return itemRepository.findByitemNMContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기
    public item itemView(String itemID){

        return itemRepository.findById(itemID).get();
    }

    // 특정 게시글 삭제
    public void itemDelete(String itemID) {

        itemRepository.deleteById(itemID);
    }

    //admin 메인 페이지
    public item mainView(String itemID){

        return itemRepository.findById(itemID).get();
    }

    public List<item> findAll(){
        List<item> list = itemRepository.findAll();

        return list;
    }

    //item 테이블에서 category 칼럼 List로 + itemID 내림차순
    public List<item> getItemsByCategory(String category) {
        List<item> items = itemRepository.findByCategory(category);
        return items.stream()
                .sorted((item1, item2) -> item2.getItemID().compareTo(item1.getItemID()))
                .collect(Collectors.toList());
    }

    // new Product 나타내기 위해 itemID 내림차순
    public List<item> getItemsDescendingOrder(int limit) {
        return itemRepository.findAll()
                .stream()
                .sorted((item1, item2) -> item2.getItemID().compareTo(item1.getItemID()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Optional<item> findItemById(String itemId) {
        return itemRepository.findById(itemId);
    }

    public Page<item> getItemsByCategory(String category, Pageable pageable) {
        return itemRepository.findByCategory(category, pageable);
    }
}
