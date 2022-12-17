package com.yoon.relayNovelopment.config;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.util.AlternativeJdkIdGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultIdGeneratorTest {

    @Test
    void keyGen() {
        DefaultIdGenerator generator = new DefaultIdGenerator(new AlternativeJdkIdGenerator());

        String key = generator.getKey(NovelBoardId.of("aaa"), Title.of("bbb"));

        assertThat(key).isEqualTo("aaa:bbb");
    }
}