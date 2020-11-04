package com.thoughtworks.capability.gtb.vo;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventVo {

  private String id;
  private String name;
  private EventType type;

  @JsonSerialize(using = DateSerializer.class)
  @JsonDeserialize(using = DateDeserializer.class)
  private Date time;

  @JsonUnwrapped
  private UserVo user;

  private static class DateSerializer extends StdSerializer<Date> {
    protected DateSerializer() {
      this(null);
    }

    protected DateSerializer(Class<Date> date) {
      super(date);
    }

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(date.getTime());
    }
  }

  private static class DateDeserializer extends StdDeserializer<Date> {
    protected DateDeserializer() {
      this(null);
    }

    protected DateDeserializer(Class<?> date) {
      super(date);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
      return new Date(jsonParser.getLongValue());
    }
  }
}
