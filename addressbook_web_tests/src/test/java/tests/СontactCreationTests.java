package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class СontactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname: List.of("","first name")){
            for (var lastname: List.of("", "last name"))
            {
                for(var address: List.of("", "contact address"))
                {
                    for(var email1: List.of("","email1@example.com"))
                    {
                        for( var email2: List.of("","email2@example.com"))
                        {
                            for(var email3: List.of("","email3@example.com"))
                            {
                                result.add(new ContactData(firstname, lastname, address, email1, email2, email3));
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i<5;i++)
        {
            result.add(new ContactData(randomString(i*10),randomString(i*10),randomString(i*10), randomString(i*10) + "@example.com",randomString(i*10) + "@example.com",randomString(i*10) + "@example.com" ));
        }
        return  result;
    }

    public static ArrayList<ContactData> negativeContactProvider(){
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("contact name'", "","","","","")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactcount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactcount = app.contact().getCount();
        Assertions.assertEquals(contactcount + 1, newContactcount);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateMultipleContacts(ContactData contact) {
        int contactcount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactcount = app.contact().getCount();
        Assertions.assertEquals(contactcount, newContactcount);
    }


}
