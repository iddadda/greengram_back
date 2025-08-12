package com.green.greengram.application.feedComment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedCommentGetRes {
//    댓글이 여러개 있냐 없냐의 정보
    private boolean moreComment;
//    댓글
    private List<FeedCommentItem> commentList;

}
