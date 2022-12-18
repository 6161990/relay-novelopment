package com.yoon.relayNovelopment.persist;

import com.yoon.relayNovelopment.utils.Serializer;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.sql.SQLException;

@WritingConverter
public class NovelValueToPgObjectConverter implements Converter<NovelsWrapperForPersist, PGobject> {

    @Override
    public PGobject convert(NovelsWrapperForPersist source) {
        String serialize = Serializer.getInstance().serialize(source);
        PGobject pGobject = new PGobject();
        pGobject.setType("json");
        try {
            pGobject.setValue(serialize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pGobject;
    }

}
