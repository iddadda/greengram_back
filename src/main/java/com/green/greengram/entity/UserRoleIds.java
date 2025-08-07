package com.green.greengram.entity;


import com.green.greengram.config.enumcode.EnumMapperType;
import com.green.greengram.config.enumcode.model.EnumUserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class UserRoleIds implements Serializable {
    private Long userId;
    @Column(length = 2 )
    private EnumUserRole roleCode;

}
