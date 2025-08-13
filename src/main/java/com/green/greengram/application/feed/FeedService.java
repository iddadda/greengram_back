package com.green.greengram.application.feed;


import com.green.greengram.application.feed.model.FeedGetDto;
import com.green.greengram.application.feed.model.FeedGetRes;
import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.application.feed.model.FeedPostRes;
import com.green.greengram.application.feedComment.FeedCommentMapper;
import com.green.greengram.application.feedComment.model.FeedCommentGetReq;
import com.green.greengram.application.feedComment.model.FeedCommentGetRes;
import com.green.greengram.application.feedComment.model.FeedCommentItem;
import com.green.greengram.config.constants.ConstComment;
import com.green.greengram.config.util.ImgUploadManager;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final FeedCommentMapper feedCommentMapper;
    private final FeedRepository feedRepository;
    private final ImgUploadManager imgUploadManager;
    private final ConstComment constComment;

    //    피드 등록
    @Transactional
    public FeedPostRes postFeed(long signedUserId, FeedPostReq req, List<MultipartFile> pics) {
        User writerUser = new User();
        writerUser.setUserId(signedUserId);

        //        피드 저장해야지
        Feed feed = Feed.builder()
                .writerUser(writerUser)
                .location(req.getLocation())
                .contents(req.getContents())
                .build();

        feedRepository.save(feed);  // feed 객체는 영속성을 갖는다

        List<String> fileNames = imgUploadManager.saveFeedPics(feed.getFeedId(), pics);
        feed.addFeedPics(fileNames);

        return new FeedPostRes(feed.getFeedId(), fileNames);

    }

//    페이징처리, 피드 리스트 받아오기
    public List<FeedGetRes> getFeedList(FeedGetDto dto) {
        List<FeedGetRes> list = feedMapper.findAllLimitedTo(dto);
//        각 피드에서 사진 가져오기, 댓글 가져오기(4개만)


        for (FeedGetRes feedGetRes : list) {
//            사진
            feedGetRes.setPics(feedMapper.findAllPicByFeedId(feedGetRes.getFeedId()));

//            댓글
            FeedCommentGetReq req = new FeedCommentGetReq(feedGetRes.getFeedId(), constComment.startIndex, constComment.size);
            List<FeedCommentItem> commentList = feedCommentMapper.findAllByFeedIdLimitedTo(req);
//            row 수가 4였을 대 true, row 가 0~3 인 경우 false => 결과에 따라 프론트에서 더보기 활성화 함
            boolean moreComment = commentList.size() == constComment.size;
            FeedCommentGetRes feedCommentGetRes = new FeedCommentGetRes(moreComment, commentList);
            feedGetRes.setComments(feedCommentGetRes);
            if (moreComment) { // 마지막 댓글 삭제
                commentList.remove(commentList.size() - 1);
            }
        }
        return list;
    }


}
