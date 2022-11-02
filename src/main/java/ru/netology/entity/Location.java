package ru.netology.entity;

import java.util.Objects;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    @Override
    public boolean equals(Object obj) {
        Location o = (Location) obj;
        if(this == obj)
            return true;
        if (o.street == null && o.country == null && o.city == null) {
            return true;
        } else if (street == null) {
            return city.equals(o.city) && country.equals(o.country);
        } else
        return city.equals(o.city) && country.equals(o.country) && street.equals(o.street);
    }

}
