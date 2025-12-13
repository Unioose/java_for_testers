package manager;


import manager.hmb.ContactRecord;
import manager.hmb.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends  HelperBase{

    private SessionFactory sessionFactory;

    public  HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                // PostgreSQL
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                // Credentials
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                // Create a new SessionFactory
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records){
       return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());

//        List<GroupData> result = new ArrayList<>();
//        for (var record : records){
//            result.add(convert(record));
//        }
//        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData(""+record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if("".equals(id)){
            id = "0";
        }

        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord",GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord",Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData().withId(""+record.id)
                .withFirstName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address);
    }

    private static ContactRecord convert(ContactData data){
        var id = data.id();
        if("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstname() , data.lastname(), data.address(), data.email(), data.email2(), data.email3());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records){
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
//        List<ContactData> result = new ArrayList<>();
//        for (var record : records){
//            result.add(convert(record));
//        }
//        return result;
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord",ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord",Long.class).getSingleResult();
        });
    }

    public List<ContactData> getContactsNotInGroup(List<ContactData> data) {
            // Получаем все контакты
            List<ContactData> allContacts = getContactList();

            List<ContactData> result = new ArrayList<>(allContacts);
            result.removeAll(data);

            //Возвращаем контакты которые не входят в выбранную группу
            return result;
    }

}
