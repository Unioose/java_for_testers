package tests;

import tools.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class СontactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        //Быстрые тесты
//        for (var firstname: List.of("","first name")) {
//            for (var lastname : List.of("", "last name")) {
//                result.add(new ContactData()
//                        .withFirstName(firstname)
//                        .withLastName(lastname));
//            }
//        }
        //Все тесты
//        for (var firstname : List.of("", "first name")) {
//            for (var lastname : List.of("", "last name")) {
//                for (var address : List.of("", "contact address")) {
//                    for (var email1 : List.of("", "email1@example.com")) {
//                        for (var email2 : List.of("", "email2@example.com")) {
//                            for (var email3 : List.of("", "email3@example.com")) {
//                                result.add(new ContactData()
//                                        .withFirstName(firstname)
//                                        .withLastName(lastname)
//                                        .withAddress(address)
//                                        .withEmail(email1, email2, email3));
//                            }
//                        }
//                    }
//                }
//            }
//        }
        //Чтение файла целиком
        var json = Files.readString(Paths.get("contacts.json"));
         ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json,  new TypeReference<List<ContactData>>(){});
        //Чтение xml
//        var mapper = new XmlMapper();
//        var value = mapper.readValue(new File("contacts.xml"),new TypeReference<List<ContactData>>(){});

        result.addAll(value);
        return result;
    }

    public static ArrayList<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData()
                        .withFirstName("contact name'")
                        .withLastName("")
                        .withAddress("")
                        .withEmail("", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        //Получение списка до попытки добавления невалидного контакта
        var oldContact = app.contact().getList();
        app.contact().createContact(contact);
        //Получение списка после попытки добавления невалидного контакта
        var newContact = app.contact().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        var expectedList = new ArrayList<>(oldContact);
        expectedList.add(contact.withId(newContact.get(newContact.size() - 1).id())
                .withAddress("")
                .withEmail("", "", ""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateMultipleContacts(ContactData contact) {
        //Получение списка до попытки добавления невалидного контакта
        var oldContact = app.contact().getList();
        app.contact().createContact(contact);
        //Получение списка после попытки добавления невалидного контакта
        var newContact = app.contact().getList();
        //Сравнение двух списков
        Assertions.assertEquals(newContact, oldContact);
    }

    @Test
    void canCreateContact()
    {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contact().createContact(contact);
    }
}
