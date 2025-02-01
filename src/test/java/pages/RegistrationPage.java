package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.component.CalendarComponent;
import pages.component.ResultsTableComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private static SelenideElement
            setFirstName = $("#firstName"),
            setLastName = $("#lastName"),
            setEmail = $("#userEmail"),
            setNumber = $("#userNumber"),
            setGender = $("#genterWrapper"),

    setCalendar = $("#dateOfBirthInput"),
            setSubjects = $("#subjectsInput"),
            setHobbies = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            setAddress = $("#currentAddress"),
            setState = $("#state"),
            setCity = $("#city"),

    submit = $("#submit");


    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    @Step("Открыть страницу")
    public RegistrationPage openPage() {
        open("automation-practice-form");
        return this;
    }
    @Step("Скрываем баннеры")
    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    @Step("Заполняем FirstName")
    public RegistrationPage setFirstName(String value) {
        setFirstName.setValue(value);
        return this;
    }
    @Step("Заполняем LastName")
    public RegistrationPage setLastName(String value) {
        setLastName.setValue(value);
        return this;
    }
    @Step("Добавляем  картинку")
    public RegistrationPage uploadPicture(String path) {
        uploadPicture.uploadFromClasspath(path);

        return this;
    }
    @Step("Заполняем Email")
    public RegistrationPage setEmail(String value) {
        setEmail.setValue(value);
        return this;
    }
    @Step("Заполняем Mobile Number")
    public RegistrationPage setNumber(String value) {
        setNumber.setValue(value);
        return this;
    }
    @Step("Заполняем Gender")
    public RegistrationPage setGender(String value) {
        setGender.$(byText(value)).click();
        return this;
    }
    @Step("Заполняем Subjects")
    public RegistrationPage setSubjects(String value) {
        setSubjects.setValue(value).pressEnter();
        return this;
    }
    @Step("Заполняем Hobbies")
    public RegistrationPage setHobbies(String value) {
        setHobbies.$(byText(value)).click();
        return this;
    }
    @Step("Заполняем Address")
    public RegistrationPage setAddress(String value) {
        setAddress.setValue(value);
        return this;
    }
    @Step("Заполняем State")
    public RegistrationPage setState(String state) {
        setState.scrollTo().click();
        setState.$(byText(state)).click();

        return this;
    }
    @Step("Заполняем City")
    public RegistrationPage setCity(String city) {
        setCity.click();
        setCity.$(byText(city)).scrollTo().click();

        return this;
    }

    @Step("Отправляем заполненную форму")
    public RegistrationPage submit() {
        submit.scrollTo().scrollTo().click();
        return this;
    }
    @Step("Заполняем Date of Birth")
    public RegistrationPage setDateOfBirthday(String day, String month, String year) {
        setCalendar.scrollTo().click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public ResultsTableComponent getResultsTableComponent() {
        return resultsTableComponent;
    }
}

