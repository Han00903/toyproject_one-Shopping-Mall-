package com.shoppingmall.toyproject_one.service;

import com.shoppingmall.toyproject_one.DTO.boardDTO;
import com.shoppingmall.toyproject_one.entity.board;
import com.shoppingmall.toyproject_one.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private  final BoardRepository boardRepository;
    public void save(boardDTO boardDTO){
        boardDTO.setUserID(boardDTO.getUserID());
        board board = com.shoppingmall.toyproject_one.entity.board.board(boardDTO);
        boardRepository.save(board);
    }

    public List<boardDTO> findAll(){
        // findAll로 repository에서 무언가를 가져올 때 entity 형식으로 받아옴
        List<board> boardList = boardRepository.findAll();
        // entity형식으로 받아온 객체를 DTO형식으로 받아 Controller로 옮겨줘야해 (-> DTO에서 생성자(?) 생성해줘)
        List<boardDTO> boardDTOList = new ArrayList<>();
        for (board board: boardList) {
            boardDTOList.add(boardDTO.boardDTO(board));
        }
        return boardDTOList;
    }

    // 조회수 처리
    @Transactional
    public void updateHits(String boardID){
        boardRepository.updateHits(boardID);
    }

    public boardDTO findByID(String boardID){
        Optional<board> optionalBoard = boardRepository.findById(boardID);
        if (optionalBoard.isPresent()){
            board board = optionalBoard.get();
            boardDTO boardDTO = com.shoppingmall.toyproject_one.DTO.boardDTO.boardDTO(board);
            return boardDTO;
        }else{
            return null;
        }
    }
}