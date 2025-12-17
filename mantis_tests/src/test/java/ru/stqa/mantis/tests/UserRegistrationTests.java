package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(){
        //Добавляем почту в James
        var user = CommonFunctions.randomString(8);
        try {
            app.jamesCli().addUser(String.format("%s@localhost", user),"password");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Заполняем форму регистрации и отправляем письмо
        app.session().registration(user);
        //Извлекаем ссылку из письма
        var messages = app.mail().receive(String.format("%s@localhost", user),"password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if(matcher.find()){
            url = text.substring(matcher.start(), matcher.end());
        }
        if (!url.isEmpty()) {
            app.driver().get(url); //Открываем ссылку из письма
        } else {
            throw new RuntimeException("Ссылка в письме не найдена");
        }
        //Подверждаем регистрацию
        var newPassword = CommonFunctions.randomString(8);
        app.session().submitRegistration( user, newPassword);
        //Авторизация и проверка что авторизация прошла успешно
        app.http().login(user, newPassword);
        app.http().isLoggedIn();




    }
}
