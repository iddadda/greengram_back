package com.green.greengram.application.feedComment;

import com.green.greengram.application.feedComment.model.FeedCommentGetReq;
import com.green.greengram.application.feedComment.model.FeedCommentGetRes;
import com.green.greengram.application.feedComment.model.FeedCommentPostReq;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/feed/comment")
public class FeedCommentController {
    private final FeedCommentService feedCommentService;

//    댓글등록
    @PostMapping
    public ResultResponse<?> postFeedComment(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody FeedCommentPostReq req) {

        log.info("signedUserId:{}", userPrincipal);
        log.info("req:{}", req);
        long feedCommentId = feedCommentService.postFeedComment(userPrincipal.getSignedUserId(), req);

        return new ResultResponse<>("댓글 등록 완료", feedCommentId);
    }

    //    댓글조회
    @GetMapping
    public ResultResponse<?> getFeedCommentList(@Valid @ModelAttribute FeedCommentGetReq req) {
        log.info("req: {}", req);
        FeedCommentGetRes feedCommentGetRes = feedCommentService.getFeedList(req);
        return new ResultResponse<>(String.format("rows: %d", feedCommentGetRes.getCommentList().size())
                , feedCommentGetRes);
    }
}
