package ir;

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
/*        person.stream()
                .filter(i -> Integer.parseInt(i.getAge()) >= 25 && Integer.parseInt(i.getAge()) <= 32)
                .forEach(System.out::println);*/




        // Map the filtered list to new list and write PersonsEmailAddresses.txt file
/*        List<String> emails = person.stream()
                .map(i -> i.getEmail())
                .sorted()
                .toList();

        try (FileWriter fw = new FileWriter("PersonsEmailAddresses.txt" )){
            for (String s: emails) {
                fw.write(s);
                fw.append("\n");
            }
            fw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }*/




    }
}
