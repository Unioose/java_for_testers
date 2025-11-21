package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.contact().getCount() == 0)
        {
            app.contact().createContact(new ContactData("TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com"));
        }
        app.contact().removeContact();
    }

    @Test
    void canRemoveAllContactAtOnce(){
        if (app.contact().getCount() == 0)
        {
            app.contact().createContact(new ContactData("TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com"));
        }
        app.contact().removeAllContact();
        Assertions.assertEquals(0, app.contact().getCount());

    }


}
