package com.green.greengram.config.model;

import com.green.greengram.config.enumcode.model.EnumUserRole;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class UserPrincipal implements UserDetails, OAuth2User {
    private final long signedUserId;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(long memberId, List<EnumUserRole> roles) {
        this.signedUserId = memberId;
        List<SimpleGrantedAuthority> list = new ArrayList<>();
//         방법 1
//        for(EnumUserRole role : roles){
//            String roleName = String.format("ROLE_%s", role.name());
//            log.info("roleName: {}", roleName);
//            list.add(new SimpleGrantedAuthority(roleName));
//        }
//        this.authorities = list;

//        방법2
        this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.name()))).toList();
    }

    @Override
    public String getPassword() { return null; }

    @Override
    public String getUsername() { return null; }

    @Override
    public String getName() { return ""; }

    @Override
    public Map<String, Object> getAttributes() { return Map.of(); }
}