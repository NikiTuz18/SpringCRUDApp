package ru.nikituz.simplespringcrudapp.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nikituz.simplespringcrudapp.entities.Person;
import ru.nikituz.simplespringcrudapp.repositories.PersonRepository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Person> findAll() {
        return this.currentSession()
                .createQuery("from Person", Person.class)
                .list();
    }

    @Override
    public Person findById(Long id) {
        return this.currentSession()
                .find(Person.class, id);
    }

    @Override
    public void save(final Person person) {
        this.currentSession()
                .persist(person);
    }

    @Override
    public void update(final Person updatePerson) {
        this.currentSession()
                .saveOrUpdate(updatePerson);
    }

    @Override
    public void delete(Long id) {
        this.currentSession()
                .remove(this.findById(id));
    }

    private Session currentSession(){
        return this.sessionFactory.getCurrentSession();
    }
}
