package com.moviestorage.moviestorage.vo;

import com.moviestorage.moviestorage.type.SexType;
import lombok.*;

import java.util.Date;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorVO {

    private int id;
    private String name;
    private Date birthday;
    private String profile;
    private SexType sex;
    private String birthplace;


    @Override
    public String toString() {
        return "id: " + id + ", name: " + name  ;
    }
}
