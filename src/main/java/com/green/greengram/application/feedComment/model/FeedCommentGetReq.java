package com.green.greengram.application.feedComment.model;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;


@Getter
@ToString
public class FeedCommentGetReq {

    @NotNull(message = "feedId 는 필수입니다.")
    @Positive
    private Long feedId;

    @NotNull(message = "startIdx 는 필수입니다.")
    @PositiveOrZero  // 0포함 1이상 값만 가능
    private Integer startIdx;

    @NotNull(message = "size 는 필수입니다.")
//    @Min(20)
//    @Max(50)
    private Integer size;

    private Integer sizePlusOne;

    @ConstructorProperties({"feed_id", "start_idx", "size"})
    public FeedCommentGetReq(Long feedId, Integer startIdx, Integer size) {
        this.feedId = feedId;
        this.startIdx = startIdx;
        this.size = size;
        this.sizePlusOne = size + 1;
    }


}
