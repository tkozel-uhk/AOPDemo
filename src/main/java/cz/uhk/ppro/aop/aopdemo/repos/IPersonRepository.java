package cz.uhk.ppro.aop.aopdemo.repos;

import cz.uhk.ppro.aop.aopdemo.model.Person;

import java.util.List;

public interface IPersonRepository {
    //save person to repository
    void save(Person person);
    //find person by id
    Person findById(long id);
    //find person by firstname
    Person findByFirstname(String firstname);
    //find person by surname
    Person findBySurname(String surname);
    //find person by firstname and surname
    Person findByFirstnameAndSurname(String firstname, String surname);
    List<Person> getAll();
}
