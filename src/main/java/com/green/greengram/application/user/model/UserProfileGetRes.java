package com.green.greengram.application.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserProfileGetRes {
    private Long userId;
    private String pic;
    private String createdAt;
    private String uid;
    private String nickName;
    private int feedCount; // 유저가 등록한 피드 수
    private int allFeedLikeCount; // 유저가 받은 모든 좋아요 수
}
