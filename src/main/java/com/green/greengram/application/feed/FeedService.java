package com.green.greengram.application.feed;


import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.application.feed.model.FeedPostRes;
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
    private final FeedRepository feedRepository;
    private final ImgUploadManager imgUploadManager;

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

//    페이징처리


}
