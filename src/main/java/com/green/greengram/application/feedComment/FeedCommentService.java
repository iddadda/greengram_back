package com.green.greengram.application.feedComment;

import com.green.greengram.application.feedComment.model.FeedCommentGetReq;
import com.green.greengram.application.feedComment.model.FeedCommentGetRes;
import com.green.greengram.application.feedComment.model.FeedCommentItem;
import com.green.greengram.application.feedComment.model.FeedCommentPostReq;
import com.green.greengram.config.constants.ConstComment;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;
    private final FeedCommentMapper feedCommentMapper;
    private final ConstComment constComment;

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

    //    댓글리스트조회
    public FeedCommentGetRes getFeedList(FeedCommentGetReq req) {
        List<FeedCommentItem> commentList = feedCommentMapper.findAllByFeedIdLimitedTo(req);
        boolean moreComment = commentList.size() > req.getSize();
        if(moreComment) { //마지막 댓글 삭제
            commentList.remove(commentList.size() - 1); //마지막 아이템 삭제
        }
        return new FeedCommentGetRes(moreComment, commentList);
    }
}
