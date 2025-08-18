package com.green.greengram.application.follow;


import com.green.greengram.application.follow.model.FollowPostReq;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/follow")
public class FollowController {
    private final FollowService followService;

//    팔로우하기
    @PostMapping
    public ResultResponse<?> postUserFollow(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody FollowPostReq req) {
        log.info("fromUserId:{}", userPrincipal.getSignedUserId());
        log.info("toUserId:{}", req.getToUserId());
        followService.postUserFollow(userPrincipal.getSignedUserId(), req.getToUserId());
        return new ResultResponse<>("팔로우 성공", null);
    }

//    팔로우 취소
    @DeleteMapping
    public ResultResponse<?> deleteUserFollow(@AuthenticationPrincipal UserPrincipal userPrincipal
                                              , @RequestParam("to_user_id") long toUserId) {
        log.info("fromUserId:{}", userPrincipal.getSignedUserId());
        log.info("toUserId:{}", toUserId);
        followService.deleteUserFollow(userPrincipal.getSignedUserId(), toUserId);
        return new ResultResponse<>("팔로우 취소", null);
    }
}
