package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<item, String> {

//아이디로 회원 정보 조회 (select * from user where user_id = ?)
    @Override
    Optional<item> findById(String itemID);

    //item의 category 컬럼에서 특정 Data 추출
    List<item> findByCategory(String category);

    Page<item> findByitemNMContaining(String SearchKeyword, Pageable pageable);
    /*
    * JPA Repository에서 findBy(컬럼이름)Containing
    * -> 컬럼에서 키워드가 포함된 것을 찾겠다
    *
    * if> 특정 컬럼이 아닌 모든 컬럼을 검색가능하도록 만들고 싶은 경우
    * 동적 쿼리를 생성할 필요가 있음
    * JPA에서 Specification을 사용하여 동적 쿼리를 생성하는 것이 일반적인 방법
    * Specification은 쿼리의 조건을 정의하는 객체
    ************************* GPT가 JPA Criteria API 사용해야한다고하는데 나중에 찾아보기
    */
        @Query("SELECT i FROM item i WHERE i.category = ?1")
        Page<item> findByCategory(String category, Pageable pageable);
}