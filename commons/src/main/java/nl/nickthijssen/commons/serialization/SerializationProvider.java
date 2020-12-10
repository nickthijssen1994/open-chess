package nl.nickthijssen.commons.serialization;

public class SerializationProvider {

	private static Serializer serializer;

	public static Serializer getSerializer() {
		if (serializer == null) {
			serializer = new GsonSerializer();
		}
		return serializer;
	}
}
