package org.springframework.social.microsoft.azure.serialization;

public class Json {
	private static JsonConverter jsonConverter;

	static {
		jsonConverter = new JsonConverter();
	}

	public static JsonConverter instance(){
		return new JsonConverter();
	}

	public static JsonConverter instance(JsonSerializationSettings serializationSettings){
		return new JsonConverter(serializationSettings);
	}

	public static <T> T fromJson(String jsonString, Class<T> targetClass){
		return jsonConverter.fromJson(jsonString, targetClass);
	}
	public static <T> T fromJson(String jsonString, Class<T> targetClass, JsonSerializationSettings serializationSettings){
		return jsonConverter.fromJson(jsonString, targetClass, serializationSettings);
	}

	public static <T> String toJson(T objectToSerialize){
		return jsonConverter.toJson(objectToSerialize);
	}

	public static <T> String toJson(T objectToSerialize, JsonSerializationSettings serializationSettings){
		return jsonConverter.toJson(objectToSerialize, serializationSettings);
	}

	public static void configure(JsonSerializationSettings serializationSettings) {
		jsonConverter.configure(serializationSettings);
	}


}
