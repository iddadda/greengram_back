package com.green.greengram.application.follow;

import com.green.greengram.entity.User;
import com.green.greengram.entity.UserFollow;
import com.green.greengram.entity.UserFollowIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {
    private final FollowRepository followRepository;

    public void postUserFollow(Long fromUserId, Long toUserId) {
        UserFollowIds userFollowIds = UserFollowIds.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .build();

        User fromUser = new User();
        fromUser.setUserId(userFollowIds.getFromUserId());
        User toUser = new User();
        toUser.setUserId(userFollowIds.getToUserId());

        UserFollow userFollow = UserFollow.builder()
                .userFollowIds(userFollowIds)
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

//        userFollow.userFollow();

        followRepository.save(userFollow);
    }
}
