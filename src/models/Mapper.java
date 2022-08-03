package models;

import pojo.Person;
import pojo.PersonDTO;

import java.util.UUID;

public class Mapper {

    public Mapper(){}

    public PersonDTO mapToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge(person.getAge());
        personDTO.setName(person.getName());
        return personDTO;
    }

    public Person mapToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setAge(personDTO.getAge());
        person.setName(personDTO.getName());
        return person;
    }

}
