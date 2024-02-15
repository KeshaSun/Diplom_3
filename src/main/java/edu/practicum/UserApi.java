package edu.practicum;
import lombok.*;

    @Getter
    @Setter
    @AllArgsConstructor
    @RequiredArgsConstructor
    @Builder
    public class UserApi {

        String email;
        String password;
        String name;
    }
