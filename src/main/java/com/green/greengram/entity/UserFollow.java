package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor  // 기본생성자 자동 생성
@EqualsAndHashCode
public class UserFollow extends CreatedAt {
    @EmbeddedId
    private UserFollowIds userFollowIds;

//    관계설정
    @ManyToOne
    @MapsId("fromUserId")
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;

    @ManyToOne
    @MapsId("toUserId")
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;


//    public void userFollow() {
//        User user = new User();
//        user.setUserId(userFollowIds.getFromUserId());
//        this.fromUser = user;
//
//        user = new User();
//        user.setUserId(userFollowIds.getToUserId());
//        this.toUser = user;
//    }



}
