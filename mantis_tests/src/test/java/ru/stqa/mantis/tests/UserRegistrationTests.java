package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase{

    public static Stream<String> RandomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }

    @Test
    void canRegisterUser(){
        //Добавляем почту в James
        var user = CommonFunctions.randomString(8);
        app.jamesApi().addUser(String.format("%s@localhost", user),"password");
//        try {
//            app.jamesCli().addUser(String.format("%s@localhost", user),"password");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
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

    @ParameterizedTest
    @MethodSource("RandomUser")
    public void canRegisterUserRestApi(String user)
    {
        //Добавляем почту в James
        var email = String.format("%s@localhost", user);
        var password = "password";
        app.jamesApi().addUser(email,password);
        //Регистрируем нового пользователя чере АПИ
        app.rest().addUser(new UserData()
                .withName(user)
                .withPassword(password)
                .withAccessLevel("reporter"));
        //Извлекаем ссылку из письма
        var messages = app.mail().receive(email,password, Duration.ofSeconds(60));
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
