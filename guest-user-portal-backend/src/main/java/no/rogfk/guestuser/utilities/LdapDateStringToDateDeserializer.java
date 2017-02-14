package no.rogfk.guestuser.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class LdapDateStringToDateDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = p.getText().trim();
            str = str.replace("-", "") + "000000Z";
            return str;
        }
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return p.getText();
        }
        throw ctxt.mappingException("LdapDateStringToDateDeserializer could not map value: " + p.getText());
    }
}
