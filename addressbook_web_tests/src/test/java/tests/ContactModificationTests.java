package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact(){
        if (app.contact().getCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", ""));
        }
        var oldContact = app.contact().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        var testData = new ContactData().withFirstName("modified first name").withLastName("modified last name");
        app.contact().modifyContact(oldContact.get(index), testData);
        var newContact = app.contact().getList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.set(index, testData.withId(oldContact.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact,expectedList);
    }
}
