package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones(){
        //Проверка что в БД есть контакты
        if (app.hbm().getContactCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.home(), contact.mobile(), contact.secondary(), contact.work())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contact().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress(){
        //Проверка что в БД есть контакты
        if (app.hbm().getContactCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && ! "".equals(s))
                        .map(s -> s.replace("\r\n", "\n")) //Может состоять из нескольких строк
                        .collect(Collectors.joining("\n"))
        ));
        var address = app.contact().getAddress();
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testEmail(){
        //Проверка что в БД есть контакты
        if (app.hbm().getContactCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contact().getEmails();
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void contactDataMainPage()
    {
        //Проверка что в БД есть контакты
        if (app.hbm().getContactCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contactOnMainPage = contacts.get(index);
        var contactInfoOnEditPage = app.contact().getContactFromEditPage(contactOnMainPage);
        var expected = Stream.of(contacts.get(index).address(),
                        contacts.get(index).home(), contacts.get(index).mobile(), contacts.get(index).secondary(), contacts.get(index).work(),
                        contacts.get(index).email(), contacts.get(index).email2(), contacts.get(index).email3())
                        .filter(s -> s != null && ! "".equals(s))
                        .map(s -> s.replace("\r\n", "\n")) //Может состоять из нескольких строк
                        .collect(Collectors.joining("\n"));
        var contactMainPageMap = Stream.of(contactOnMainPage.address(),
                        contactOnMainPage.home(), contactOnMainPage.mobile(), contactOnMainPage.secondary(), contactOnMainPage.work(),
                        contactOnMainPage.email(), contactOnMainPage.email2(), contactOnMainPage.email3())
                .filter(s -> s != null && ! "".equals(s))
                .map(s -> s.replace("\r\n", "\n")) //Может состоять из нескольких строк
                .collect(Collectors.joining("\n"));
        var contactEditPageMap = Stream.of(contactInfoOnEditPage.address(),
                        contactInfoOnEditPage.home(), contactInfoOnEditPage.mobile(), contactInfoOnEditPage.secondary(), contactInfoOnEditPage.work(),
                        contactInfoOnEditPage.email(), contactInfoOnEditPage.email2(), contactInfoOnEditPage.email3())
                .filter(s -> s != null && ! "".equals(s))
                .map(s -> s.replace("\r\n", "\n")) //Может состоять из нескольких строк
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, contactEditPageMap);
        Assertions.assertEquals(expected, contactMainPageMap);
        Assertions.assertEquals(contactEditPageMap, contactMainPageMap);
    }
}
