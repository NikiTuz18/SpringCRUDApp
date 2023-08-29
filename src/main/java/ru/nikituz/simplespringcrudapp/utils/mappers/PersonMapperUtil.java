package ru.nikituz.simplespringcrudapp.utils.mappers;

import lombok.experimental.UtilityClass;
import ru.nikituz.simplespringcrudapp.dto.PersonDto;
import ru.nikituz.simplespringcrudapp.entities.Person;

@UtilityClass
public class PersonMapperUtil {

    public static Person dtoToEntity(PersonDto personDto){
        return Person.builder()
                .name(personDto.getName())
                .age(personDto.getAge())
                .email(personDto.getEmail())
                .build();
    }

    public static PersonDto entityToDto(Person person){
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .email(person.getEmail())
                .build();
    }
}
