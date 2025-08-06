package com.green.greengram.config.enumcode;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 기본생성자를 private 하여 객체화를 막음
public class EnumConvertUtils {

//    Enum to String
//    Code to Enum

    //    String(Code값) to Enum
//    리턴타입이 Enum이어야 하고 EnumMapperType을 상속받은 Enum이어야 한다.
//    enumClass는 Enum 타입이어야 한다.
    public static <E extends Enum<E> & EnumMapperType> E ofCode(Class<E> enumClass, String code) {
    if (StringUtils.isBlank(code)) {return null;}

//    Enum에 있는 값 중 매개변수 code와 같은 값을 찾아 리턴하기 위함. 근데 같은게 없으면 return null
    return EnumSet.allOf(enumClass).stream()  // enum을 Stream을 하기 위함
            .filter(item -> item.getCode().equals(code))
            .findFirst().orElse(null);
    }

//    Enum to String (Code 값)
    public static <E extends Enum<E> & EnumMapperType> String toCode(E enumItem) {
        if(enumItem == null) {return null;}
        return enumItem.getCode();
    }
}
