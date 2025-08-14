package com.green.greengram.application.follow.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FollowPostReq {
    @Positive
    @NotNull(message = "toUserId 는 필수입니다.")
    private Long toUserId;

}
