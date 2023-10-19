package cz.uhk.ppro.aop.aopdemo.repos;

import cz.uhk.ppro.aop.aopdemo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//Person repository holding data in a list
@Repository
public class PersonRepository implements IPersonRepository {
    //list of persons
    private List<Person> persons = new ArrayList<>(
            List.of(
            new Person(1, "John", "Smith"),
            new Person(2, "Jane", "Smith"),
            new Person(3, "John", "Doe"),
            new Person(4, "Jane", "Doe")
        )
    );

    @Override
    public void save(Person person) {
        persons.add(person);
    }

    @Override
    public Person findById(long id) {
        return persons.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Person findByFirstname(String firstname) {
        return persons.stream().filter(p -> p.getFirstname().equals(firstname)).findFirst().orElse(null);
    }

    @Override
    public Person findBySurname(String surname) {
        return persons.stream().filter(p -> p.getSurname().equals(surname)).findFirst().orElse(null);
    }

    @Override
    public Person findByFirstnameAndSurname(String firstname, String surname) {
        return persons.stream()
                .filter(
                        p -> p.getFirstname().equals(firstname)
                                && p.getSurname().equals(surname)
                )
                .findFirst()
                .orElse(null);
    }

    public List<Person> getAll() {
        return persons;
    }

}
