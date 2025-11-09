package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class СontactCreationTests extends TestBase{

    @Test
    public void canCreateContact() {
        app.contact().createContact(new ContactData("TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com"));
    }

    @Test
    public void canCreateEmptyContact() {
        app.contact().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFirstNameOnly() {
        app.contact().createContact(new ContactData().withFirstName("First Name User"));
    }

    @Test
    public void canCreateContactWithLastNameOnly() {
        app.contact().createContact(new ContactData().withFirstName("Last Name User"));
    }

    @Test
    public void canCreateContactWithAddressOnly() {
        app.contact().createContact(new ContactData().withAddress("New York"));
    }

    @Test
    public void canCreateContactWitEmailOnly() {
        app.contact().createContact(new ContactData().withEmail("text@example.com","text2@example.com","text3@example.com"));
    }


}
