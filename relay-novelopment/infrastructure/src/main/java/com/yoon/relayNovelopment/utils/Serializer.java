package com.yoon.relayNovelopment.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Serializer {
    private static final String EMPTY = "";
    private static Serializer serializer;
    private Gson gson;

    private Serializer() {
        this.build();
    }

    public static synchronized Serializer getInstance() {
        if (serializer == null) {
            serializer = new Serializer();
        }

        return serializer;
    }

    public String serialize(Object object) {
        if (object == null) {
            return "";
        } else if ("".equals(object)) {
            return "";
        } else {
            return object instanceof String ? (String)object : this.gson().toJson(object);
        }
    }

    public <T> T deserialize(String aSerialization, Class<T> aType) {
        return this.gson().fromJson(aSerialization, aType);
    }

    protected Gson gson() {
        return this.gson;
    }

    private void build() {
        this.gson = (new GsonBuilder()).registerTypeAdapter(Date.class, new Serializer.DateSerializer()).registerTypeAdapter(Date.class, new Serializer.DateDeserializer()).registerTypeAdapter(LocalDateTime.class, new Serializer.LocalDateTimeSerializer()).registerTypeAdapter(LocalDateTime.class, new Serializer.LocalDateTimeDeserializer()).serializeNulls().create();
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        private DateDeserializer() {
        }

        public Date deserialize(JsonElement json, Type typeOfTarget, JsonDeserializationContext context) throws JsonParseException {
            long time = Long.parseLong(json.getAsJsonPrimitive().getAsString());
            return new Date(time);
        }
    }

    private static class DateSerializer implements JsonSerializer<Date> {
        private DateSerializer() {
        }

        public JsonElement serialize(Date source, Type typeOfSource, JsonSerializationContext context) {
            return new JsonPrimitive(Long.toString(source.getTime()));
        }
    }

    private static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        private LocalDateTimeDeserializer() {
        }

        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_DATE_TIME);
        }
    }

    private static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private LocalDateTimeSerializer() {
        }

        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }
}
