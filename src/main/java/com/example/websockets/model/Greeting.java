package com.example.websockets.model;

public class Greeting {
    private String name;
    public Greeting() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Greeting(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                ", name='" + name + '\'' +
                '}';
    }
}
