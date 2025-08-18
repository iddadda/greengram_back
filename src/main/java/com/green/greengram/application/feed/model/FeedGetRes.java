package com.green.greengram.application.feed.model;

import com.green.greengram.application.feedComment.model.FeedCommentGetRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class FeedGetRes {
    private long feedId;
    private String contents;
    private String location;
    private String createdAt;
    private long writerUserId;
    private String writerUid;
    private String writerNickName;
    private String writerPic;
    private int isLike;  // 0 이면 좋아요 x, 1 이면 좋아요 o
    private int likeCount;  // 해당 피드의 좋아요 수
    private List<String> pics;

//    피드에 달려있는 댓글정보
    private FeedCommentGetRes comments;
}
