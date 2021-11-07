package model;

public class Address {

    private String street;
    private String city;
    private String number;

    public Address(String street, String city, String number) {
        this.street = street;
        this.city = city;
        if (number.matches("^([\\d]{1,4}([\\w])?)[\\\\|/]?(([\\d]){1,4}[(\\w)]|[\\w]?)?$")) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("Wrong number format! : " + number);
        }

    }


    @Override
    public String toString() {
        return "{"+
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
