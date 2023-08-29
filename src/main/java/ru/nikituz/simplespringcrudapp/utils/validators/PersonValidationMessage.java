package ru.nikituz.simplespringcrudapp.utils.validators;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonValidationMessage {

    public static final String NAME_REGEXP = "^([А-ЯЁ][а-яё]+[\\-\\s]?){2,}$";
    public static final String NAME_ERROR_MESSAGE = "Укажите полную Фамилию, Имя и Отчество(если есть).";

    public static final String AGE_ERROR_MESSAGE = "Возраст не может быть меньше 0";

    public static final String EMAIL_NOT_EMPTY_ERROR = "Поле email не должно быть пустым";
    public static final String EMAIL_ERROR = "Адрес электронной почты должен быть действительным";

}
