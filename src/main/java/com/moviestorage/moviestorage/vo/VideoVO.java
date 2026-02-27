package com.moviestorage.moviestorage.vo;

import com.moviestorage.moviestorage.type.MediaType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.List;

//enum media_type { MOVIE, TV }
@Entity(name = "videos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Comment("제목")
    @Column(nullable = false, length = 100)
    private String title;

    @Comment("감독")
    @Column(length = 100)
    private String directors;

//    @Comment("배우")
//    @Column(length = 30)
//    private String casting;

    @Comment("배우 리스트")
    @Column(length = 100)
    @OneToMany
    private List<ActorVO> castActors;

    @Comment("한줄요약")
    @Column()
    private String summary;

    @Comment("한줄요약")
    @Column(columnDefinition = "TEXT", length = 100000)
    private String overview;

    @Comment("관람연령등급")
    @Column(length = 30)
    private String ageRatings;

    @Comment("평점")
    @Column(length = 30)
    @ColumnDefault("0")
    private float score;

    @Comment("분류")
    @Column(length = 30)
    private MediaType mediaType;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        return sb.append("ID: ").append(id)
                .append(", title: ").append(title)
                .append(", directors: ").append(directors)
//                .append(", castActors: ").append(castActors.toString())
                .append(", summary: ").append(summary)
                .append(", overview: ").append(overview)
                .append(", ageRatings: ").append(ageRatings)
                .append(", score: ").append(score)
                .append(", mediaType: ").append(mediaType)
                .toString();
//        return String.format("ID: %d, title: {}, directors: {}, castActors: {}, summary: {}, overview: {}, ageRatings: {}, score: {}, mediaType: {}",
//                id, title, directors, castActors.toString(), summary, overview, ageRatings, score, mediaType);
    }

  /*

    public void createActorList() {
//        String[] actorIdArr = this.casting.split(",");
//        castActors = new ArrayList<>();
//        for (String id_actor : actorIdArr) {
//            String[] id_actor_arr = id_actor.split("_");
//            ActorVO actor = ActorVO.builder()
//                    .id(Integer.parseInt(id_actor_arr[0]))
//                    .name(id_actor_arr[1])
//                    .build();
//            castActors.add(actor);
//        List<ActorVO> actorList = actorList
        }

    }
*/


}
