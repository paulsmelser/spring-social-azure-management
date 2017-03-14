package org.springframework.social.microsoft.azure.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {
	private ObjectMapper objectMapper;

	public JsonConverter(){
		objectMapper = JsonSerializationSettings.builder()
				.with(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.with(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
				.with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
				.with(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
				.with(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.with(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
				.with(new JavaTimeModule())
				.build().createMapper();
	}
	public JsonConverter(JsonSerializationSettings serializationSettings) {
		this.objectMapper = serializationSettings.createMapper();
	}

	public <T> T fromJson(String jsonString, Class<T> targetClass){
		try {
			ObjectReader reader = objectMapper
					.reader()
					.forType(targetClass);

			return reader.readValue(jsonString);

		} catch (IOException e) {
			throw new JsonSerializationException(e);
		}
	}
	public <T> T fromJson(String jsonString, Class<T> targetClass, JsonSerializationSettings serializationSettings){
		try {
			ObjectReader reader = serializationSettings.createMapper()
					.reader()
					.forType(targetClass);

			return reader.readValue(jsonString);

		} catch (IOException e) {
			throw new JsonSerializationException(e);
		}
	}

	public <T> String toJson(T objectToSerialize){
		try {

			return objectMapper.writer().writeValueAsString(objectToSerialize);
		} catch (JsonProcessingException e) {
			throw new JsonSerializationException(e);
		}
	}

	public <T> String toJson(T objectToSerialize, JsonSerializationSettings serializationSettings){
		try {

			return serializationSettings.createMapper().writer().writeValueAsString(objectToSerialize);
		} catch (JsonProcessingException e) {
			throw new JsonSerializationException(e);
		}
	}

	public void configure(JsonSerializationSettings serializationSettings) {
		objectMapper = serializationSettings.createMapper();
	}
}
