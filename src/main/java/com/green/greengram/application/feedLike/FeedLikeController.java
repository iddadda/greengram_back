package com.green.greengram.application.feedLike;

import com.green.greengram.application.feedLike.model.FeedLikeToggleReq;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/feed/like")
public class FeedLikeController {
    private final FeedLikeService feedLikeService;

    @PostMapping
    public ResultResponse<?> toggle(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody FeedLikeToggleReq req) {
        log.info("signedUserId:{}", userPrincipal.getSignedUserId());
        log.info("req:{}", req.getFeedId());
        boolean result = feedLikeService.toggle(userPrincipal.getSignedUserId(), req);
        
//        true 이면 좋아요, false 이면 좋아요 취소
        return new ResultResponse<>(result ? "좋아요 처리" : "좋아요취소", result);
    }
}
