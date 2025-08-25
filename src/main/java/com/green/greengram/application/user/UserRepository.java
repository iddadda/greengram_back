package com.green.greengram.application.user;

import com.green.greengram.config.security.SignInProviderType;
import com.green.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
//    SignInProvideType 소셜 로그인 어떤걸로 했는지 정보(구글? 네이버? 카카오?)
    User findByUidAndProviderType(String uid, SignInProviderType signInProviderType);
}
