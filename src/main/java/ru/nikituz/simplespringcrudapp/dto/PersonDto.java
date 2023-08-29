package ru.nikituz.simplespringcrudapp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static ru.nikituz.simplespringcrudapp.utils.validators.PersonValidationMessage.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    private long id;

    @Pattern(regexp = NAME_REGEXP, message = NAME_ERROR_MESSAGE)
    private String name;

    @Min(value = 0, message = AGE_ERROR_MESSAGE)
    private int age;

    @NotEmpty(message = EMAIL_NOT_EMPTY_ERROR)
    @Email(message = EMAIL_ERROR)
    private String email;
}
