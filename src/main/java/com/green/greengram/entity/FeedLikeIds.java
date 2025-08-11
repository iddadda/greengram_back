package com.green.greengram.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FeedLikeIds implements Serializable {
    private Long feedId;
    private Long userId;
}
