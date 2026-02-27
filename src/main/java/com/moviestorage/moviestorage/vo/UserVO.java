package com.moviestorage.moviestorage.vo;

import com.moviestorage.moviestorage.type.GenderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;


@Entity(name = "users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Comment("사용자 계정 ID")
    @Column(nullable = false, length = 30)
    private String username;

    @Comment("비밀번호")
    @Column(nullable = false, length = 30)
    private String password;

    @Comment("사용자 성명")
    @Column(nullable = false, length = 30)
    private String name;

    @Comment("나이")
    @Column(nullable = false)
    private int age;

    @Comment("성별")
    @Column(nullable = false)
    private GenderType gender;
//    private String sex;


}

