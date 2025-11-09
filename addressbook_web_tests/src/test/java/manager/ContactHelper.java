package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{
    public  ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact() {
        initContactCreation();
        //fillContactForm(contact);
        //submitContactCreation();
       // returnToContactPage();
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

}
