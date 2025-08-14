package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import com.green.greengram.config.enumcode.model.EnumUserRole;
import com.green.greengram.config.model.JwtUser;
import com.green.greengram.config.util.ImgUploadManager;
import com.green.greengram.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImgUploadManager imgUploadManager;
    private final UserMapper userMapper;

    //    signUp
    @Transactional
    public void signUp(UserSignUpReq req, MultipartFile pic) {
        String hashedPassword = passwordEncoder.encode(req.getUpw());

        User user = new User();
        user.setNickName(req.getNickName());
        user.setUid(req.getUid());
        user.setUpw(hashedPassword);
        user.addUserRoles(req.getRoles());

        userRepository.save(user);

        if(pic != null) {
            String savedFileName = imgUploadManager.saveProfilePic(user.getUserId(), pic);
            user.setPic(savedFileName);
        }
    }

//    signIn
    public UserSignInDto signIn(UserSignInReq req) {
        User user = userRepository.findByUid(req.getUid());
        if(user == null || !passwordEncoder.matches(req.getUpw(), user.getUpw())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디/비밀번호를 확인해주세요.");
        }

        List<EnumUserRole> roles = user.getUserRoles().stream().map(item -> item.getUserRoleIds().getRoleCode()).toList();
        JwtUser jwtUser = new JwtUser(user.getUserId(), roles);

        UserSignInRes userSignInRes = UserSignInRes.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .pic(user.getPic())
                .build();

        return UserSignInDto.builder()
                .jwtUser(jwtUser)
                .userSignInRes(userSignInRes)
                .build();
    }

    //    프로필 조회
    public UserProfileGetRes getProfileUser(UserProfileGetDto dto) {
        return userMapper.findProfileByUserId(dto);
    }
}