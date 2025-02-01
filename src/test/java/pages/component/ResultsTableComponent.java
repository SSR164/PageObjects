package pages.component;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {
    @Step("Проверяем, что модальное окно появилось")
    public void checkModalAppears() {
        $(".modal-content").should(appear);
        $(".modal-content .h4").shouldHave(text("Thanks for submitting the form"));
    }
    @Step("Проверяем, что в таблице {key}={value} ")
    public void checkResult(String key, String value) {
        $(".modal-body .table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }
    @Step("Проверяем, что модальное окно НЕ появилось")
    public void checkResultNegative() {
        $(".modal-body .table-responsive").shouldNot(visible);
    }
}