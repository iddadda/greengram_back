package com.green.greengram.application.user.model;

import com.green.greengram.config.enumcode.model.EnumUserRole;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class UserSignUpReq {
//    @Size(min=4, max=50, message = "아이디는 4~50자까지 작성 가능합니다")  // 밸리데이션 기능
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,50}$", message = "아이디는 영어, 숫자, 언더바로만 구성되어야 하며 4~50자까지 작성할 수 있습니다.")
    private String uid;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
            message = "비밀번호는 영문/숫자/특수문자 최소 1개씩 포함, 8~16자로 설정해주세요. (특수문자 일부 제외)")
    private String upw;

    @Pattern(regexp = "^[가-힣]{2,10}$", message = "닉네임은 한글로 2~10자까지 입력 가능합니다." )
    private String nickName;

    private List<EnumUserRole> roles;

}
