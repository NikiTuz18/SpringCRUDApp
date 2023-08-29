package ru.nikituz.simplespringcrudapp.repositories;

import ru.nikituz.simplespringcrudapp.entities.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findAll();

    Person findById(Long id);

    void save(Person person);

    void update(Person updatePerson);

    void delete(Long id);
}
