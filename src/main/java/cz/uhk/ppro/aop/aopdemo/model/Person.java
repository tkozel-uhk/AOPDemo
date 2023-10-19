package cz.uhk.ppro.aop.aopdemo.model;

/**
 * Jednoduché POJO Osoby
 */
public class Person {
    long id;
    private String firstname;
    private String surname;

    public Person(long id, String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
        this.id = id;
    }

    public Person() {
        this.firstname = "";
        this.surname = "";
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return firstname + " " + surname + " (" + id + ")";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
