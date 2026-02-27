package com.moviestorage.moviestorage.service;

import com.moviestorage.moviestorage.type.GenderType;
import com.moviestorage.moviestorage.type.MediaType;
import com.moviestorage.moviestorage.vo.ActorVO;
import com.moviestorage.moviestorage.vo.VideoVO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;
    @Autowired
    ActorService actorService;

    @BeforeAll
    static void before() throws ParseException {

    }

    @DisplayName("제목으로 영상 찾기 테스트")
    @Rollback(value = false)
    @Test
    void findByTitleTest() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

        ActorVO actor1 = ActorVO.builder()
                .name("에밀리아 클라크")
                .gender(GenderType.F)
                .birthday(simpleDateFormat.parse("1986.10.23"))
                .build();
        ActorVO actor2 = ActorVO.builder()
                .name("키트 해링턴")
                .gender(GenderType.M)
                .birthday(simpleDateFormat.parse("1986.12.26"))
                .build();

        actorService.save(actor1);
        actorService.save(actor2);


        VideoVO video = VideoVO.builder()
                .title("왕좌의 게임")
                .directors("데이비드 베니오프")
                .summary("겨울이 오고 있다")
                .ageRatings("18")
                .score(84)
                .overview("2011년 4월 HBO에서 방영을 시작한 판타지물. 조지 R.R. 마틴의 얼음과 불의 노래를 원작으로 한다. 웨스테로스 대륙의 7개의 국가와 하위 몇 개의 국가들로 구성된 연맹 국가인 칠 왕국의 통치권, 철 왕좌를 차지하기 위한 싸움을 그려낸 드라마이다. 왕좌를 차지하기 위한 귀족 가문의 다툼 외에도 북부에 존재하는 미지의 위협인 백귀, 그리고 15년 전에 내전으로 인해 살해된 왕의 딸인 대너리스 타르가르옌이 자신이 추방당한 웨스테로스를 향해 왕권 회복을 도전하는 이야기가 함께 들어있다.")
                .mediaType(MediaType.TV)
                .castActors(Arrays.asList(actor1, actor2))
                .build();

        VideoVO videoVO = videoService.save(video);

        String keyword = "왕좌";


        List<VideoVO> videoList = videoService.findByTitle(keyword);
        assertThat(videoList.getFirst().toString())
                .isEqualTo(videoVO.toString())
                ;








    }
}