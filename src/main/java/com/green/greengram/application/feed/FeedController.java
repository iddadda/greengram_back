package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

//    피드 등록
    @PostMapping
    public ResultResponse<?> postFeed(@AuthenticationPrincipal UserPrincipal userPrincipal
                                    , @Valid @RequestPart FeedPostReq req
                                    , @RequestPart(name = "pic") List<MultipartFile> pics){

        log.info("signedUserId:{}", userPrincipal.getSignedUserId());
        log.info("req:{}", req);
        log.info("pics.size():{}", pics.size());

        feedService.postFeed(userPrincipal.getSignedUserId(), req, pics);

        return new ResultResponse<>("피드등록완료", 1);
    }
}
