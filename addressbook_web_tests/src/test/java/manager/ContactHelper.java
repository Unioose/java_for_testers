package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactHelper extends HelperBase{
    public  ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void removeContact(ContactData contact) {
        openContactPage();
        selectContact(contact);
        removeSeletedContacts();
        returnToContactPage();
    }

    private void removeSeletedContacts() {
        click(By.name("delete"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void openContactPage() {
        if (!manager.isElementPresent(By.name("Send e-Mail"))) {
            click(By.linkText("home"));
        }
    }

    private void returnToContactPage() {
        click(By.linkText("home page"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        if(!Objects.equals(contact.photo(), ""))
        {
            attach(By.name("photo"), contact.photo());
        }
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContact() {
        openContactPage();
        selectAllContatcs();
        removeSeletedContacts();
    }

    private void selectAllContatcs() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox: checkboxes){
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        openContactPage();
        var contacts = new ArrayList<ContactData>();
        var tds = manager.driver.findElements(By.name("entry"));
        for (var cells: tds){
            var td = cells.findElements(By.tagName("td"));
            var FirstName = td.get(2).getText();
            var LastName = td.get(1).getText();
            var checkbox = cells.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(FirstName).withLastName(LastName));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactPage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactPage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }
}
