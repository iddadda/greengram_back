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
        UserFollowIds userFollowIds = getIds(fromUserId, toUserId);

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

    public void deleteUserFollow(Long fromUserId, Long toUserId) {
        UserFollowIds userFollowIds = getIds(fromUserId, toUserId);

        followRepository.deleteById(userFollowIds);
    }


    //    follow 관계인 userId 를 받는 메소드 생성 (동일한 코드 반복으로 인해 메소드로 만듦)
    private UserFollowIds getIds(long fromUserId, long toUserId) {
        return UserFollowIds.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .build();

    }
}