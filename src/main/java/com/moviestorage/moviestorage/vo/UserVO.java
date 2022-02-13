package com.moviestorage.moviestorage.vo;

import com.moviestorage.moviestorage.type.SexType;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class UserVO {

    private int id;
    private String username;
    private String name;
    private int age;
    private SexType sex;
//    private String sex;

}

