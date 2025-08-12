package com.green.greengram.application.feedComment;

import com.green.greengram.application.feedComment.model.FeedCommentPostReq;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;

//    댓글등록
    public long postFeedComment(Long signedUserId, FeedCommentPostReq req) {
        User userId = new User();
        userId.setUserId(signedUserId);

        Feed feedId = Feed.builder()
                .feedId(req.getFeedId())
                .build();

        FeedComment feedComment = FeedComment.builder()
               .comment(req.getComment())
               .userId(userId)
                .feedId(feedId)
               .build();

        feedCommentRepository.save(feedComment);

        return feedComment.getFeedCommentId();
    }
}
