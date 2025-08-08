package com.green.greengram.application.feed.model;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class FeedPostReq {

    @Size(max = 1000)
    private String contents;

    @Size(max = 30)
    private String location;
}
