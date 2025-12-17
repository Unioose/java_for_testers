package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

public class JamesTest extends TestBase{

    @Test
    void canCreateUser() throws InterruptedException {
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(8)),"password");
    }
}
