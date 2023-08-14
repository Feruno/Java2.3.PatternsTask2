package test;

import com.codeborne.selenide.Condition;
import dataTest.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static dataTest.DataGenerator.*;
import static dataTest.DataGenerator.Regist.generetRegUser;
import static dataTest.DataGenerator.Regist.generetUser;

public class AppIbank {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void appSuccessfulPath() {
        var regUser = generetRegUser("active");
        $("[data-test-id=login] input").setValue(regUser.getLogin());
        $("[data-test-id=password] input").setValue(regUser.getPassword());
        $("button.button").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    void appUnregisteredUser() {
        var notRegUser = generetUser("active");
        $("[data-test-id=login] input").setValue(notRegUser.getLogin());
        $("[data-test-id=password] input").setValue(notRegUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }

    @Test
    void appBlockedUser() {
        var blockedRegUser = generetRegUser("blocked");
        $("[data-test-id=login] input").setValue(blockedRegUser.getLogin());
        $("[data-test-id=password] input").setValue(blockedRegUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"))
                .shouldBe(Condition.visible);
    }

    @Test
    void appWrongUser() {
        var notRegUser = generetUser("active");
        var wrongLogin = randLogin();
        $("[data-test-id=login] input").setValue(wrongLogin);
        $("[data-test-id=password] input").setValue(notRegUser.getPassword());
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }

    @Test
    void appWrongPass() {
        var regUser = generetRegUser("active");
        var wrongPass = randPassword();
        $("[data-test-id=login] input").setValue(regUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongPass);
        $("button.button").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }

}
