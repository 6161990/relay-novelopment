package com.yoon.relayNovelopment.config;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.util.AlternativeJdkIdGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultIdGeneratorTest {

    @Test
    void key는_novelBoard_title_조합으로_만들어진다() {
        DefaultIdGenerator generator = new DefaultIdGenerator(new AlternativeJdkIdGenerator());

        String key = generator.getKey(NovelBoardId.of("novelBoardId"), Title.of("그해 우리는"));

        assertThat(key).isEqualTo("novelBoardId:그해 우리는");
    }
}