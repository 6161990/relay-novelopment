package com.yoon.relayNovelopment.persist;

import com.yoon.relayNovelopment.domain.Opening;
import com.yoon.relayNovelopment.utils.Serializer;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.sql.SQLException;

public class OpeningConverters {
    public static OpeningReadingConverter READING = new OpeningReadingConverter();
    public static OpeningWritingConverter WRITING = new OpeningWritingConverter();

    @ReadingConverter
    private static class OpeningReadingConverter implements Converter<PGobject, Opening> {

        @Override
        public Opening convert(PGobject source) {
            return Serializer.getInstance().deserialize(source.getValue(), Opening.class);
        }
    }
    @WritingConverter
    private static class OpeningWritingConverter implements Converter<Opening, PGobject> {

        @Override
        public PGobject convert(Opening source) {
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
