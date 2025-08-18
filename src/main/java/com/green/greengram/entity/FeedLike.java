package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FeedLike extends CreatedAt {
    @EmbeddedId
    private FeedLikeIds id;

    @ManyToOne
    @MapsId("feedId")
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feedId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
