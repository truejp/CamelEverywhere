package com.example.kamelrechner;

public class CamelDB {

    private String name;
    private int age, weight, body, eye, hair, breast, gender, result;
    private long id;

    public CamelDB(String name, int age, int weight, int body, int eye, int hair, int breast, int gender, long id, int result) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.body = body;
        this.breast = breast;
        this.hair = hair;
        this.eye = eye;
        this.gender = gender;
        this.id = id;
        this.result = result;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getWeight() {
        return weight;
    }
    public int getBody() {
        return body;
    }
    public int getEye() {
        return eye;
    }
    public int getHair() {
        return hair;
    }
    public int getBreast() {
        return breast;
    }
    public int getGender() {
        return gender;
    }
    /* @Override
    public String toString() {
        String output = name + age + weight;
        return output;
    } */
}
