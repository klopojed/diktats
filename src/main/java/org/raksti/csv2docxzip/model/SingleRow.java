package org.raksti.csv2docxzip.model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@SuppressWarnings("unused")
public class SingleRow {
    private final long id;
    private final UUID uuid;
    private final String text;
    private final String firstNames;
    private final String lastName;
    private final String email;
    private final String age;
    private final String country;
    private final String city;
    private final String phone;
    private final String language;
    private final String education;

    public SingleRow(long id, String text, String firstNames, String lastName, String email, String age, String country, String city, String phone, String language, String education) {
        this.id = id;
        this.email = email;
        this.text = text;
        this.firstNames =  firstNames;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.language = language;
        this.education = education;
        this.uuid = UUID.randomUUID();
    }

    public SingleRow(@NotNull String[] row) {
        this(Long.parseLong(row[0]), row[1], row[2], row[3], row[4], row[5], row[12], row[13], row[20], row[22], row[23]);
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUuid() {
        return uuid.toString();
    }

    public String getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getLanguage() {
        return language;
    }

    public String getEducation() {
        return education;
    }
}
