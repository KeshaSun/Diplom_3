package edu.practicum;
import lombok.*;

    @Data
    @AllArgsConstructor
    @Builder
    public class User {

        String email;
        String password;
        String name;
    }
