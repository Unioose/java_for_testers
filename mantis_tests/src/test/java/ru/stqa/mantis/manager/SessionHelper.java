package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public  SessionHelper(ApplicationManager manager){
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"),user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"),password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElentPresent(By.cssSelector("span.user-info"));
    }

    public void registration(String user) {
        click(By.cssSelector(".back-to-login-link"));
        type(By.name("username"),user);
        type(By.name("email"),user+"@localhost");
        click(By.xpath("//input[@value='Signup']"));
    }

    public void submitRegistration(String user, String newPassword) {
        type(By.name("realname"),user);
        type(By.name("password"),newPassword);
        type(By.name("password_confirm"),newPassword);
        click(By.cssSelector("button[type='submit']"));
    }
}
