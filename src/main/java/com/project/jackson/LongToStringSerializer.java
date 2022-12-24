package com.project.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author: weihaoming
 * @description: Long转string 避免返回前端丢失精度
 * @create: 2022-08-29 10:43:45
 **/

public class LongToStringSerializer extends JsonSerializer<Long> {


    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null && value.toString().length() > 16) {
            gen.writeString(value.toString());
        } else {
            gen.writeNumber(value);
        }
    }
}
