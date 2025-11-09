package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void removeContact() {
        openContactPage();
        selectContact();
        removeSeletedContact();
        returnToContactPage();
    }

    private void removeSeletedContact() {
        click(By.name("delete"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
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
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }
}
