package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class СontactCreationTests extends TestBase{
    @Test
    public void canCreateContact() {
        app.contact().createContact();
    }
}
