package com.yoon.relayNovelopment.persist;

import com.yoon.relayNovelopment.domain.Articles;
import com.yoon.relayNovelopment.utils.Serializer;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.sql.SQLException;

public class ArticlesConverters {

    public static ArticlesReadingConverter READING = new ArticlesReadingConverter();
    public static ArticlesWritingConverter WRITING = new ArticlesWritingConverter();

    @ReadingConverter
    private static class ArticlesReadingConverter implements Converter<PGobject, Articles> {

        @Override
        public Articles convert(PGobject source) {
            return Serializer.getInstance().deserialize(source.getValue(), Articles.class);
        }
    }

    @WritingConverter
    private static class ArticlesWritingConverter implements Converter<Articles, PGobject> {

        @Override
        public PGobject convert(Articles source) {
            String json = Serializer.getInstance().serialize(source);
            PGobject jsonObject = new PGobject();
            jsonObject.setType("json");

            try {
                jsonObject.setValue(json);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
    }

}
