package com.green.greengram.application.feed.model;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class FeedPostReq {

    @Size(max = 1000, message = "constents 는 1,000 자 이하여야 합니다.")
    private String contents;

    @Size(max = 30, message = "location 은 30 자 이하여야 합니다.")
    private String location;
}
