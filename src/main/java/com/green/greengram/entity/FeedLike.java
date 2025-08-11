package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FeedLike extends CreatedAt {
    @EmbeddedId
    FeedLikeIds feedLikeIds;

    @ManyToOne
    @MapsId("feedId")
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feedId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
