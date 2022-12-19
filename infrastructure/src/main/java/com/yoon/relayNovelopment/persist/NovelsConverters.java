package com.yoon.relayNovelopment.persist;

import com.yoon.relayNovelopment.domain.Novels;
import com.yoon.relayNovelopment.utils.Serializer;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.sql.SQLException;

public class NovelsConverters {

    public static NovelsReadingConverter READING = new NovelsReadingConverter();
    public static NovelsWritingConverter WRITING = new NovelsWritingConverter();

    @ReadingConverter
    private static class NovelsReadingConverter implements Converter<PGobject, Novels> {

        @Override
        public Novels convert(PGobject source) {
            return Serializer.getInstance().deserialize(source.getValue(), Novels.class);
        }
    }

    @WritingConverter
    private static class NovelsWritingConverter implements Converter<Novels, PGobject> {

        @Override
        public PGobject convert(Novels source) {
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
