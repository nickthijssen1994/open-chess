package nl.nickthijssen.commons.serialization;

import java.lang.reflect.Type;

public interface Serializer<R> {

	<T> T deserialize(String data, Class<T> clazz);

	<T> T deserialize(String data, Type type);

	R serialize(Object object);
}
