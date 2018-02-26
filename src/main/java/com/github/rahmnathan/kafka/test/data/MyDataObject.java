package com.github.rahmnathan.kafka.test.data;

import java.io.Serializable;
import java.util.Objects;

public class MyDataObject implements Serializable {
    private String id;
    private long offset;
    private long timestamp;
    private String address;
    private String name;
    private int age;

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
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
        return offset == that.offset &&
                timestamp == that.timestamp &&
                age == that.age &&
                Objects.equals(id, that.id) &&
                Objects.equals(address, that.address) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offset, timestamp, address, name, age);
    }

    @Override
    public String toString() {
        return "MyDataObject{" +
                "id='" + id + '\'' +
                ", offset=" + offset +
                ", timestamp=" + timestamp +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
