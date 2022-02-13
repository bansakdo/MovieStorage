package com.moviestorage.moviestorage.vo;

import com.moviestorage.moviestorage.type.VideoType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//enum media_type { MOVIE, TV }

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoVO {

    private int id;
    private String title;
    private String directors;
    private String casting;
    private List<ActorVO> cast_actor;
    private String summary;
    private String overview;
    private String ageRate;
    private float score;
    private VideoType mediaType;


    public void createActorList() {
        String[] id_actors_arr = this.casting.split(",");
        cast_actor = new ArrayList<>();
        for (String id_actor : id_actors_arr) {
            String[] id_actor_arr = id_actor.split("_");
            ActorVO actor = ActorVO.builder()
                    .id(Integer.parseInt(id_actor_arr[0]))
                    .name(id_actor_arr[1])
                    .build();
            cast_actor.add(actor);
        }

    }


}
