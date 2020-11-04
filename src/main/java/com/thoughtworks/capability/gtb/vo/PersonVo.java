package com.thoughtworks.capability.gtb.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonVo {

  private String id;

  @JsonSerialize(nullsUsing = PersonAgeSerializer.class)
  private Integer age;

  private String name;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String hobby;

  private static class PersonAgeSerializer extends StdSerializer<Integer> {
    protected PersonAgeSerializer() {
      this(null);
    }

    protected PersonAgeSerializer(Class<Integer> age) {
      super(age);
    }

    @Override
    public void serialize(Integer age, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(0);
    }
  }
}
