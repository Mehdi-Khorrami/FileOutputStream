package ir;

import ir.calculate.Calculate;
import ir.modals.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Person> person = new ArrayList<>();

        try (Stream<String> file = Files.lines(Paths.get("./StreamPracticeWithCSVFile.csv"))) {
            person = file.skip(1)
                    .map(i -> {
                        String[] s = i.split(",");
                        return new Person(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9]);
                    }).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Person objects to include only those where the age is between 25 and 32(inclusive)
        person.stream()
                .filter(i -> Integer.parseInt(i.getAge()) >= 25 && Integer.parseInt(i.getAge()) <= 32)
                .forEach(System.out::println);


        // Map the filtered list to new list and write PersonsEmailAddresses.txt file
        List<String> emails = person.stream()
                .map(i -> i.getEmail())
                .sorted()
                .toList();

        try (FileWriter fw = new FileWriter("PersonsEmailAddresses.txt")) {
            for (String s : emails) {
                fw.write(s);
                fw.append("\n");
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Calculate and print the average salary of all Person objects in the list.
        System.out.println("average Salary is : " + Calculate.averageSalary(person));


        //Find and print the Person object with the highest salary.
        System.out.println(Calculate.highestSalary(person));


        // Determine and print the count of Person objects for each unique geography value
        List<String> gh = person.stream()
                .map(i -> i.getGeography())
                .distinct()
                .toList();

        for (int l = 0; l < gh.size(); l++) {
            int finalL = l;
            Long co = person.stream()
                    .filter(i -> i.getGeography().equals(gh.get(finalL)))
                    .count();
            System.out.println(gh.get(finalL) + " count is : " + co);
        }


        //Find and print the number of males and females in the list
        System.out.println(Calculate.numberOfType(person, "Male"));
        System.out.println(Calculate.numberOfType(person, "Female"));

        //Filter the list to include only alive persons and calculate the total salary of those persons
        System.out.println(Calculate.averageSalaryAlive(person));


        //Calculate and print the total salary for each unique profession in the list
        List<String> un = person.stream()
                .map(i -> i.getProfession())
                .distinct()
                .toList();

        for (int l = 0; l < un.size(); l++) {
            int finalL = l;
            Long lg = person.stream()
                    .filter(i -> i.getProfession().equals(un.get(finalL)))
                    .map(i -> Long.parseLong(i.getSalary()))
                    .reduce((i, j) -> i + j)
                    .get();

            System.out.println(un.get(finalL) + " is : " + lg);
        }


    }
}
