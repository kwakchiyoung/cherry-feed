package com.bazzi.cherryfeed.apps.post.service;

import com.bazzi.cherryfeed.apps.calendar.domain.CoupleCalendar;
import com.bazzi.cherryfeed.apps.post.domain.Post;
import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.apps.post.dto.PageResponse;
import com.bazzi.cherryfeed.apps.post.dto.PostRequestDto;
import com.bazzi.cherryfeed.apps.post.dto.PostResponseDto;
import com.bazzi.cherryfeed.apps.calendar.repository.CoupleCalendarRepository;
import com.bazzi.cherryfeed.apps.post.repository.PostRepository;
import com.bazzi.cherryfeed.apps.account.repository.AccountRepository;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final CoupleCalendarRepository coupleCalendarRepository;

    public String createPost(Long id, PostRequestDto postRequestDto) {
        Account fidedUser = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)); //유저
        CoupleCalendar coupleCalendar = coupleCalendarRepository.findById(postRequestDto.getCalendarId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Post post = Post.builder()
                .postNm(postRequestDto.getPostNm())
                .postContent(postRequestDto.getPostContent())
                .location(postRequestDto.getLocation())
                .imgId(postRequestDto.getImgId())
                .createdById(fidedUser)
                .calId(coupleCalendar)
                .build();
        postRepository.save(post);
        return "게시글 등록완료.";
    }

    @Transactional
    public String updatePost(Long id, PostRequestDto postRequestDto) {
        Long calendarId = postRequestDto.getCalendarId();
        CoupleCalendar coupleCalendarId = coupleCalendarRepository.findById(calendarId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        post.updatePost(
                coupleCalendarId,
                postRequestDto.getPostNm(),
                postRequestDto.getPostContent(),
                postRequestDto.getLocation(),
                postRequestDto.getImgId()
        );
        return "게시글 수정 완료.";
    }

    @Transactional
    public String deletePost(Long id) {
        postRepository.deleteById(id);
        return "게시글 삭제완료.";
    }

    public PageResponse findAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));//0페이지에서 3개 가져와
        Page<Post> page = postRepository.findAll(pageRequest);

        long totalElements = page.getTotalElements(); //토탈 카운트
        ArrayList<PostResponseDto> postResponseDtos = new ArrayList<>();
        List<Post> content = page.getContent(); //3개 데이터들
        for (Post post : content) {
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .postAt(post.getCreatedAt())
                    .postContent(post.getPostContent())
                    .postView(post.getPostView())
                    .postNm(post.getPostNm())
                    .scrap(post.getScrap())
                    .id(post.getId())
                    .location(post.getLocation())
                    .imgId(post.getImgId())
                    .build();
            postResponseDtos.add(postResponseDto);
        }

        return PageResponse.builder()
                .content(postResponseDtos)
                .last(page.isLast())
                .pageNo(pageNo)
                .totalElements(totalElements)
                .totalPages(page.getTotalPages())
                .pageSize(pageSize)
                .build();
    }
}
