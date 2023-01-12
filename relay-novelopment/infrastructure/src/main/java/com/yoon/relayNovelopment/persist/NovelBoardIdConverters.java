package com.yoon.relayNovelopment.persist;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public class NovelBoardIdConverters {
    public static NovelBoardIdReadingConverter READING = new NovelBoardIdReadingConverter();
    public static NovelBoardIdWritingConverter WRITING = new NovelBoardIdWritingConverter();

    @ReadingConverter
    private static class NovelBoardIdReadingConverter implements Converter<String, NovelBoardId> {

        @Override
        public NovelBoardId convert(String source) {
            return NovelBoardId.of(source);
        }
    }

    @WritingConverter
    private static class NovelBoardIdWritingConverter implements Converter<NovelBoardId, String> {

        @Override
        public String convert(NovelBoardId source) {
            return source.getId();
        }
    }
}
