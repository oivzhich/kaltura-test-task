package types;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.User;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {
    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
            IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("objectType", user.getObjectType());
        jsonGenerator.writeStringField("username", user.getUsername());
        jsonGenerator.writeStringField("firstName", user.getFirstName());
        jsonGenerator.writeStringField("lastName", user.getLastName());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("address", user.getAddress());
        jsonGenerator.writeStringField("city", user.getCity());
        jsonGenerator.writeNumberField("countryId", user.getCountryId());
        jsonGenerator.writeStringField("externalId", user.getExternalId());
        jsonGenerator.writeEndObject();
    }
}
