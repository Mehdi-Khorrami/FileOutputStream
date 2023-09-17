package ir.calculate;

import ir.modals.Person;

import java.util.List;


public class Calculate {

    public static Double averageSalary(List<Person> person) {
        return person.stream()
                .mapToDouble(i -> Double.parseDouble(i.getSalary()))
                .average()
                .getAsDouble();
    }

    public static Person highestSalary(List<Person> person) {

        return person.stream()
                .reduce((a, b) -> {
                    if (Integer.parseInt(a.getSalary()) > Integer.parseInt(b.getSalary())) {
                        return a;
                    }
                    return b;
                })
                .get();
    }

    public static Long numberOfType(List<Person> person, String gender) {

        return person.stream()
                .filter(i -> i.getGender().equals(gender))
                .count();

    }

    public static Double averageSalaryAlive(List<Person> person) {

        List<Person> alivePerson = person.stream()
                .filter(i -> i.getIsAlive().equals("0"))
                .toList();

        //System.out.println(alivePerson.size());

        return averageSalary(alivePerson);

    }


}
