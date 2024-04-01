package ru.netology.molchanov.ApiOperationHistory.domain;


import java.io.Serializable;

public class Customer implements Serializable {
    private int id;
    private String name;
    private int age;


    public Customer(int id, String name, int age) {
        this.id = id;
        this.name = name;
        if (age < 18 || age > 80) {
            this.age = 18;
        } else {
            this.age = age;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

}
