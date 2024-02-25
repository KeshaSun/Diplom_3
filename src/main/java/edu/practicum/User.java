package edu.practicum;
import lombok.*;

    @Data
    @AllArgsConstructor
    @RequiredArgsConstructor
    @Builder
    public class User {

        String email;
        String password;
        String name;
    }
