package com.green.greengram.entity;

import com.green.greengram.config.enumcode.model.EnumUserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 30)
    private String nickName;

    @Column(length=50, nullable = false, unique = true)
    private String uid;

    @Column(length = 100)
    private String pic;

    @Column(length = 100,  nullable = false)
    private String upw;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>(1);


    public void addUserRoles(List<EnumUserRole> enumUserRole) {
        for(EnumUserRole e : enumUserRole) {
            UserRoleIds ids = new UserRoleIds(this.userId, e);
            UserRole userRole = new UserRole(ids, this);

            this.userRoles.add(userRole);
        }
    }
}
