package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class СontactCreationTests extends TestBase{

    @Test
    public void canCreateContact() {
        app.contact().createContact(new ContactData("TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com"));
    }


}
