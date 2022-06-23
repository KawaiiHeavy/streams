import models.Streams;
import pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("Alex", 20);
        Person person2 = new Person("Tanya", 15);
        Person person3 = new Person("Egor", 17);
        Person person4 = new Person("Vlad", 10);

        Person person5 = new Person("SuperPerson", 16){
            @Override
            public String getName() {
                return "my name: " + super.getName();
            }
        };

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);

        Streams<Person> streams = Streams.of(personList);

        System.out.println("personList before = " + Streams.getStreamList());

        System.out.println("map = " + streams.filter(p -> p.getAge() > 15)
                .transform(p -> new Person(p.getName(), p.getAge() + 2))
                .toMap(p -> ((Person)p).getName(), p -> p));

        System.out.println("personList after: " + personList);

        // Вспомогательный метод, чтобы смотреть, в каком состоянии коллекция в стриме сейчас
        System.out.println("list: " + Streams.getStreamList());
    }
}
