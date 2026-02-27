package com.moviestorage.moviestorage.vo;

import com.moviestorage.moviestorage.type.GenderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Entity(name = "actors")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Comment("배우명")
    @Column(length = 20, nullable = false)
    private String name;

    @Comment("생년월일")
    @Column
    private Date birthday;

    @Comment("프로필")
    @Column
    private String profile;

    @Comment("성별")
    @Column(nullable = false)
    private GenderType gender;

    @Comment("출생")
    @Column
    private String birthplace;


    @Override
    public String toString() {
        return "id: " + id + ", name: " + name  ;
    }
}
