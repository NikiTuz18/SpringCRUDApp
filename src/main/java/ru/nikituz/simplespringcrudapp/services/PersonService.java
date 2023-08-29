package ru.nikituz.simplespringcrudapp.services;

import ru.nikituz.simplespringcrudapp.dto.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto getById(Long id);

    List<PersonDto> getAll();

    void create(PersonDto personDto);

    void updateById(Long id, PersonDto updatedPersonDto);

    void deleteById(Long id);
}
