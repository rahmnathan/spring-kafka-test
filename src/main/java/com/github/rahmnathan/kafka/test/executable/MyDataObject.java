package com.github.rahmnathan.kafka.test.executable;

import java.io.Serializable;
import java.util.Objects;

public class MyDataObject implements Serializable {
    private static final long serialVersionUID = 42L;
    private final String name;
    private final String address;
    private final int age;

    public MyDataObject(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDataObject that = (MyDataObject) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, address, age);
    }

    @Override
    public String toString() {
        return "MyDataObject{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
