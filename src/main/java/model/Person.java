package model;

import exception.DateNofFoundException;
import exception.NeverRentException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(exclude = "rooms")
@Getter
@Setter
public class Person implements Serializable {

    private static final long serialVersionUID = 2619746779516180098L;

    private List<Room> rooms = new ArrayList<>();
    private String firstName;
    private String surname;
    private transient String peselNumber;
    private transient Address address;
    private transient LocalDate birthDate;
    private LocalDate firstRentDate;
    public static List<Person> personExtention = new ArrayList<>();

    public Person(String firstName, String surname, String peselNumber, Address address, LocalDate birthDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
        if (peselNumber.matches("^[0-9]{2}([02468]1|[13579][012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{5}$")) {
            this.peselNumber = peselNumber;
        } else {
            throw new IllegalArgumentException(peselNumber + "this pesel number is incorrect!");
        }
        personExtention.add(this);
    }

    public static List<Room> getRooms(Person person) {
        return Optional.ofNullable(person.getRooms()).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    private LocalDate getOldestDate(List<LocalDate> dates) {
        return Optional.ofNullable(rooms).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .map(Room::getDateOfRenting)
                .min(Comparator.naturalOrder()).orElseThrow(() -> new DateNofFoundException("Could not find a date!"));
    }

    public LocalDate getFirstRentDate() {
        if (rooms.isEmpty()) {
            throw new NeverRentException("Person "
                    + this.getFirstName()
                    + " " + this.getSurname()
                    + " has no rented rooms!");
        }
        return firstRentDate;
    }

}