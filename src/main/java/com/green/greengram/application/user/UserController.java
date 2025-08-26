package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import com.green.greengram.config.jwt.JwtTokenManager;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;


    //   회원가입
    @PostMapping("/sign-up")
    public ResultResponse<?> signUp(@Valid @RequestPart UserSignUpReq req, @RequestPart(required = false) MultipartFile pic) {
        log.info("req:{}", req);
        log.info("pic:{}", pic != null ? pic.getOriginalFilename() : pic);
        userService.signUp(req, pic);
        return new ResultResponse<Integer>("", 1);
    }

    //    로그인
    @PostMapping("/sign-in")
    public ResultResponse<?> signIn(@Valid @RequestBody UserSignInReq req, HttpServletResponse response) {
        log.info("req:{}", req);

        UserSignInDto userSignInDto = userService.signIn(req);
        jwtTokenManager.issue(response, userSignInDto.getJwtUser());

        return new ResultResponse<>("sign-in 성공", userSignInDto.getUserSignInRes());
    }

// 로그아웃
    @PostMapping("/sign-out")
    public ResultResponse<?> signOut(@AuthenticationPrincipal UserPrincipal userPrincipal, HttpServletResponse response) {
        userService.signOut(userPrincipal.getSignedUserId());
        jwtTokenManager.signOut(response);
        return new ResultResponse<>("sign-out 성공", null);
    }

//    로그인 연장
    @PostMapping("/reissue")
    public ResultResponse<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        jwtTokenManager.reissue(request, response);
        return new ResultResponse<>("AccessToken 재발행 성공", null);
    }

    //    프로필 조회
    @GetMapping("/profile")
    public ResultResponse<?> getProfileUser(@AuthenticationPrincipal UserPrincipal userPrincipal
            , @RequestParam("profile_user_id") long profileUserId) {
        log.info("profileUserId: {}", profileUserId);
        UserProfileGetDto dto = new UserProfileGetDto(userPrincipal.getSignedUserId(), profileUserId);
        UserProfileGetRes userProfileGetRes = userService.getProfileUser(dto);
        return new ResultResponse<>("프로파일 유저 정보", userProfileGetRes);
    }

//    profile 사진 수정
    @PatchMapping("/profile/pic")
    public ResultResponse<?> patchProfilePic(@AuthenticationPrincipal UserPrincipal userPrincipal
                                            , @RequestPart MultipartFile pic) {
        String saveFileName = userService.patchProfilePic(userPrincipal.getSignedUserId(), pic);
        return new ResultResponse<>("profile 사진 수정 완료", saveFileName);
    }


//    DeleteMapping - /profile/pic
//    profile 있는 폴더 삭제
    @DeleteMapping("/profile/pic")
    public ResultResponse<?> deleteProfilePic(@AuthenticationPrincipal UserPrincipal userPrincipal) {
         userService.deleteProfilePic(userPrincipal.getSignedUserId());

        return new ResultResponse<>("profile 사진 삭제 완료", null);
    }

}

