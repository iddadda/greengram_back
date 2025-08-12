package com.green.greengram.application.feedComment;

import com.green.greengram.application.feedComment.model.FeedCommentGetReq;
import com.green.greengram.application.feedComment.model.FeedCommentItem;
import com.green.greengram.entity.FeedComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    List<FeedCommentItem> findAllByFeedIdLimitedTo(FeedCommentGetReq req);
}
