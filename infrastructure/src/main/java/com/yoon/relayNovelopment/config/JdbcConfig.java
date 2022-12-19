package com.yoon.relayNovelopment.config;

import com.yoon.relayNovelopment.persist.NovelBoardIdConverters;
import com.yoon.relayNovelopment.persist.NovelsConverters;
import com.yoon.relayNovelopment.persist.OpeningConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJdbcAuditing
public class JdbcConfig extends AbstractJdbcConfiguration {

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(this.getCustomConverters());
    }

    private List<Converter<?, ?>> getCustomConverters() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(NovelBoardIdConverters.READING);
        converters.add(NovelBoardIdConverters.WRITING);

        converters.add(NovelsConverters.READING);
        converters.add(NovelsConverters.WRITING);

        converters.add(OpeningConverters.READING);
        converters.add(OpeningConverters.WRITING);

        return converters;
    }
}
