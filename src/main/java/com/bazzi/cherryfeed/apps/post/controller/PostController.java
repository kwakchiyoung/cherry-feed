package com.bazzi.cherryfeed.apps.post.controller;

import com.bazzi.cherryfeed.apps.post.dto.PageResponse;
import com.bazzi.cherryfeed.apps.post.dto.PostRequestDto;
import com.bazzi.cherryfeed.apps.post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = "기념일")
@RestController
@RequestMapping(value = "/api/v1/post", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시물등록")
    @PostMapping("/post-post")
    public ResponseEntity<String> createAnvsy(Authentication authentication, @RequestBody PostRequestDto postRequestDto) {
        //(고객아이디,dto)를 게시글등록 서비스에 보낸 후 응답메세지를 가져온다.
        return ResponseEntity.ok().body(postService.createPost(Long.parseLong(authentication.getName()), postRequestDto));
    }

    @ApiOperation(value = "게시물수정")
    @PutMapping("/post-post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        //게시글 아이디와 dto를 게시글수정 서비스에 보낸 후 응답메세지를 가져온다.
        return ResponseEntity.ok().body(postService.updatePost(id, postRequestDto));
    }

    @ApiOperation(value = "게시물삭제")
    @DeleteMapping("/post-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        //게시글 아이디를 게시글삭제 서비스에 보낸 후 응답메세지를 가져온다.
        return ResponseEntity.ok().body(postService.deletePost(id));
    }

    @ApiOperation(value = "게시물조회")
    @GetMapping
    public ResponseEntity<PageResponse> find(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize
    ) {
        //페이지 넘버와,사이즈를 조회서비스에 보낸 후 페이징 처리가된 데이터를 가져온다.
        return ResponseEntity.ok().body(postService.findAll(pageNo, pageSize));
    }

}
