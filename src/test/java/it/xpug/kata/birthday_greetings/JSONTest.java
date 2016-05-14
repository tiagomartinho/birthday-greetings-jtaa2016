package it.xpug.kata.birthday_greetings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSONTest {

    String json = "{  \"last_name\": \"Brown\",  \"first_name\": \"Harry\",  \"date_of_birth\": \"1976/02/29\",  \"email\": \"harry.b@mycorp.com\",  \"fax_number\": \"\"}";

    @Test
    public void extractFirstValue() throws Exception {
        String lastName = new JSONUtilites(json).extractValue("last_name");

        assertEquals(lastName, "Brown");
    }

    @Test
    public void extractLastValue() throws Exception {
        String email = new JSONUtilites(json).extractValue("email");

        assertEquals(email, "harry.b@mycorp.com");
    }

    @Test
    public void extractEmptyValue() throws Exception {
        String faxNumber = new JSONUtilites(json).extractValue("fax_number");

        assertEquals(faxNumber, "");
    }
}
