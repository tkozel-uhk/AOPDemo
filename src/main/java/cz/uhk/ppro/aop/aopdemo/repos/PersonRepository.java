package cz.uhk.ppro.aop.aopdemo.repos;

import cz.uhk.ppro.aop.aopdemo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositář osob
 */
@Repository
public class PersonRepository implements IPersonRepository {
    //list of persons
    private List<Person> persons = new ArrayList<>(
            List.of(
            new Person(1, "David", "Novotný"),
            new Person(2, "Ilona", "Maříková"),
            new Person(3, "Antonín", "Pačes"),
            new Person(4, "Helena", "Fíková")
        )
    );

    @Override
    public void save(Person person) {
        if (person.getId() == 0) {
            person.setId(getNewId());
        } else {
            persons.removeIf(p -> p.getId() == person.getId());
        }
        persons.add(person);
        persons.sort((a,b) -> (int) (a.getId() - b.getId()));
    }

    private long getNewId() {
        return persons.stream().mapToLong(Person::getId).max().orElse(0) + 1;
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
