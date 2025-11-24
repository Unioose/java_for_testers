package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.contact().getCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", ""));
        }
        var oldContact = app.contact().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        app.contact().removeContact(oldContact.get(index));
        var newContact = app.contact().getList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.remove(index);
        Assertions.assertEquals(newContact,expectedList);



    }

    @Test
    void canRemoveAllContactAtOnce(){
        if (app.contact().getCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", ""));
        }
        app.contact().removeAllContact();
        Assertions.assertEquals(0, app.contact().getCount());

    }


}
