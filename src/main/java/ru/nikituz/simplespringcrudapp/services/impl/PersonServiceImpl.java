package ru.nikituz.simplespringcrudapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikituz.simplespringcrudapp.dto.PersonDto;
import ru.nikituz.simplespringcrudapp.entities.Person;
import ru.nikituz.simplespringcrudapp.repositories.PersonRepository;
import ru.nikituz.simplespringcrudapp.services.PersonService;
import ru.nikituz.simplespringcrudapp.utils.mappers.PersonMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public PersonDto getById(Long id) {
        return PersonMapperUtil.entityToDto(this.personRepository.findById(id));
    }

    @Override
    @Transactional
    public List<PersonDto> getAll() {
        return this.personRepository.findAll().stream()
                .map(PersonMapperUtil::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(PersonDto personDto) {
        Person person = PersonMapperUtil.dtoToEntity(personDto);
        this.personRepository.save(person);
    }

    @Override
    @Transactional
    public void updateById(Long id, PersonDto updatedPersonDto) {
        Person updatedPerson = PersonMapperUtil.dtoToEntity(updatedPersonDto);
        updatedPerson.setId(id);
        this.personRepository.update(updatedPerson);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.personRepository.delete(id);
    }
}
