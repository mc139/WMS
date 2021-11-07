package model;

import exception.DateNofFoundException;
import exception.NeverRentException;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Person {

    private List<Room> rooms = new ArrayList<>();
    private String firstName;
    private String surname;
    private String peselNumber;
    private Address address;
    private LocalDate birthDate;
     private LocalDate firstRentDate;

    //todo peselNumber validation
    public Person(String firstName, String surname, String peselNumber, Address address, LocalDate birthDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
        if (peselNumber.matches("^[0-9]{2}([02468]1|[13579][012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{5}$")) {
            this.peselNumber = peselNumber;
        } else {
            throw new IllegalArgumentException(peselNumber + "this pesel number is incorect!");
        }
    }

    private LocalDate getOldestDate(List<LocalDate> dates) {
        return Optional.ofNullable(rooms).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .map(Room::getDateOfRenting)
                .min(Comparator.naturalOrder()).orElseThrow(() -> new DateNofFoundException("Could not find a date!"));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstRentDate(LocalDate firstRentDate) {
        this.firstRentDate = firstRentDate;
    }

    public LocalDate getFirstRentDate() {
        if (rooms.isEmpty()|| rooms.size() ==0) {
            throw new NeverRentException("Person "
                    + this.getFirstName()
                    + " " + this.getSurname()
                    + " has no rented rooms!");
        }
        return firstRentDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", peselNumber='" + peselNumber + '\'' +
                ", address=" + address +
                ", birthDate=" + birthDate +
                ", firstRentDate=" + firstRentDate +
                '}';
    }
}