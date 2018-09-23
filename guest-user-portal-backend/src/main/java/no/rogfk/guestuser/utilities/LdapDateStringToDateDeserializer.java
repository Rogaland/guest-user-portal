package no.rogfk.guestuser.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import no.rogfk.ldap.utilities.LdapTimestamp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LdapDateStringToDateDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = p.getText().trim();
            SimpleDateFormat isoFormatter = new SimpleDateFormat("dd/MM/yyyy");//new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat ldapFormatter = new SimpleDateFormat(LdapTimestamp.LDAP_TIMESTAMP_FORMAT);
            try {
                Date isoDate = isoFormatter.parse(str);
                return ldapFormatter.format(isoDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return p.getText();
        }
        throw ctxt.mappingException("LdapDateStringToDateDeserializer could not map value: " + p.getText());
    }
}
