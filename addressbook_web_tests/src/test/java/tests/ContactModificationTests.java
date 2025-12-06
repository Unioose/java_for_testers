package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact(){
        if (app.hbm().getContactCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", ""));
        }
        var oldContact = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        var testData = new ContactData().withFirstName("modified first name").withLastName("modified last name");
        app.contact().modifyContact(oldContact.get(index), testData);
        var newContact = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.set(index, testData.withId(oldContact.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact,expectedList);
    }

    @Test
    void canAddContactInGroup(){
        var contact = new ContactData();
        //Проверка что в БД есть контакты
        if (app.hbm().getContactCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", ""));
        }
        //Проверка что в БД есть группы
        if (app.hbm().getGroupCount() == 0)
        {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        //Получить список групп
        var GroupsList = app.hbm().getGroupList();
        //Взять случайную группу
        var rnd = new Random();
        var indexG = rnd.nextInt(GroupsList.size());
        var group = app.hbm().getGroupList().get(indexG);
        //Получить список связанных контактов с группой
        var OldRelated = app.hbm().getContactsInGroup(group);
        //Получить список контактов не входящих в группу
        var ContactListNotInGroup = app.hbm().getContactsNotInGroup(OldRelated);
        //Проверка что список не пустой, если пустой создаем контакт
        if (ContactListNotInGroup.isEmpty())
        {
            //Создаем новый контакт и берем его в качестве добавления к группе
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", ""));
            var  newContactList = app.hbm().getContactList();
            contact =  newContactList.get(newContactList.size() - 1);
        }
        else
        {
            var indexС = rnd.nextInt(ContactListNotInGroup.size());
            contact = ContactListNotInGroup.get(indexС);
        }
        //Добавление контакта в группу
        app.contact().addInGroup(contact, group);
        //Получение нового списка контактов входящих в группу
        var NewRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(OldRelated);
        expectedList.add(contact);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        expectedList.sort(compareById);
        Assertions.assertEquals(NewRelated, expectedList);
    }

    @Test
    void canRemoveContactFromGroup()
    {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        //Проверка что в БД есть контакты
        if (app.hbm().getContactCount() == 0)
        {
            app.contact().createContact(new ContactData("", "TestName", "LastName", "Test Adress 123", "text@example.com","text2@example.com","text3@example.com", ""));
        }
        //Проверка что в БД есть группы
        if (app.hbm().getGroupCount() == 0)
        {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        //Получить список групп
        var GroupsList = app.hbm().getGroupList();
        //Взять случайную группу
        var rnd = new Random();
        var indexG = rnd.nextInt(GroupsList.size());
        var group = app.hbm().getGroupList().get(indexG);
        //Получить список связанных контактов с группой
        var OldRelated = app.hbm().getContactsInGroup(group);
        //Проверка что список не пустой, если пустой создаем контакт с привязкой к группе
        if (OldRelated.isEmpty())
        {
            app.contact().createContact(contact, group);
            var  newContactList = app.hbm().getContactList();
            contact =  newContactList.get(newContactList.size() - 1);
        }
        else
        {
            var indexС = rnd.nextInt(OldRelated.size());
            contact = OldRelated.get(indexС);
        }
        //Удаление контакта из группы
        app.contact().RemoveFromGroup(contact, group);

        //Получение нового списка контактов входящих в группу
        var NewRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(OldRelated);
        expectedList.remove(contact);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        expectedList.sort(compareById);
        Assertions.assertEquals(NewRelated, expectedList);
    }

}
