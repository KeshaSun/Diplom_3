package edu.practicum;
import lombok.*;

    @Getter
    @Setter
    @AllArgsConstructor
    @RequiredArgsConstructor
    @Builder
    public class User {

        String email;
        String password;
        String name;
    }
