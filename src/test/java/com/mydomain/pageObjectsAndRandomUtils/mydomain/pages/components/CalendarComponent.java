package com.mydomain.pageObjectsAndRandomUtils.mydomain.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
//        $(".react-datepicker__day--0" + day +
//                ":not(.react-datepicker__day--outside-month)").click(); // можно так
        String dayLocator = format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day); // а можно прописать формат в переменную
        $(dayLocator).click(); // и затем вызвать метод .click у переменной
        // %s - это замена строки на параметр, %d - заменап числа на параметр

//        $("[aria-label$='" + month + " " + day + "th, " + year + "']").click(); // можно так
//        но это не будет работать со всеми числами месяца из-за th/st/nd на конце дня в дате

//        String dayLocator = format("[aria-label$='%s %sth, " + year + "']", month, day, year); // а можно прописать формат в переменную
//        $(dayLocator).click(); // и затем вызвать метод .click у переменной
//        //и это тоже не лучший вариант из=за все тех же th/st/nd в числах месяца


    }

}
