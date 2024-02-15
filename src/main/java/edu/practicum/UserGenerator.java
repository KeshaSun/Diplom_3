package edu.practicum;

import com.github.javafaker.Faker;

public class UserGenerator {

    static Faker faker = new Faker();

    public static UserApi randomUser(){
        return new UserApi().builder()
                .email(faker.internet().emailAddress())
                .password(faker.bothify("56???????"))
                .name(faker.bothify("??????"))
                .build();
    }
}
