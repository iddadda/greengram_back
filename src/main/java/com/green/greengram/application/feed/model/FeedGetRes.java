package com.green.greengram.application.feed.model;

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
    private List<String> pics;
}
