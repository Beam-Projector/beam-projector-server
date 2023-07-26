package com.projet.beamprojector.comment.repository;

import com.projet.beamprojector.domain.entity.Board;
import com.projet.beamprojector.domain.entity.Comment;
import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.dto.comment.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 게시판 번호를 이용하여 댓글 조회
    List<Comment> findAllByBoardId(Long boardNum);


//    Optional<Comment> findById(CommentDto.CommentResponse commentNum);

    // 게시판 객체를 이용하여 댓글 조회
//    List<Comment> findAllByBoard(Board board);

/*     번호이용
     장점 : 간단하고 직관적, 가벼운 쿼리생성조회 가능
     단점 : 번호만으로는 boardEntity의 다른 정보들을 조회하기 어려움. ex) 게시판이름 등 추가정보 필요 시 별도의 쿼리 작성필요

     객체(boardEntity)이용
     장점 : boardEntity 모든 정보 활용 가능. ex) 게시판이름,설명 등 함께 사용가능 , 댓글조회와 관련된 다른기능들도 더 쉽게 구현 가능
     단점 : 객체를 로딩해야 하기 때문에 더많은 정보를 가져오기 위해 복잡한 쿼리를 생성해야할 수 있음. 미리 로딩해야하는경우 추가적인 db작업 필요*/

}