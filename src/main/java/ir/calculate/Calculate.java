package ir.calculate;

import ir.modals.Person;

import java.util.List;
import java.util.Optional;

public class Calculate {

    public static Double averageSalary(List<Person> person) {
        return person.stream()
                .mapToDouble(i -> Double.parseDouble(i.getSalary()))
                .average()
                .getAsDouble();
    }
}
