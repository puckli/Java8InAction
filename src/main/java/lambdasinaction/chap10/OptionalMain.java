package lambdasinaction.chap10;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {

        return Optional.ofNullable(persons).stream()
                .flatMap(List::stream)
                .map(Person::getCar)
                .flatMap(Optional::stream)
                .map(Car::getInsurance)
                .map(o -> o.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(toSet())
                //.flatMap(Optional::stream)
                //.map(Insurance::getName).collect(Collectors.toSet())
                ;

        //return persons.stream()
        //              .map(Person::getCar)
        //              .map(optCar -> optCar.flatMap(Car::getInsurance))
        //              .map(optInsurance -> optInsurance.map(Insurance::getName))
        //              .flatMap(Optional::stream)
        //              .collect(toSet());
    }

    public static void main(String[] args) {
        System.out.println(new OptionalMain().getCarInsuranceNames(null));
    }
}
