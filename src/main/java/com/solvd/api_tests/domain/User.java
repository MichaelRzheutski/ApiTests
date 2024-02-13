package com.solvd.api_tests.domain;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;

    public User(int id,
                String firstName,
                String lastName,
                String maidenName,
                int age,
                String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
