package com.example.board.boardservice.service;

import com.example.board.boardservice.dto.PostDto;
import com.example.board.boardservice.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    // 생성
    Post createPost(PostDto postDto);

    // 조회
    Optional<Post> getPost(Long id);
    List<Post> getAllPosts();

    // 수정
    Optional<Post> updatePost(Long id, PostDto postDto);

    // 삭제
    Optional<Void> deletePost(Long id, String password);

}