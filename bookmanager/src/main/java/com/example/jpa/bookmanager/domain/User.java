package com.example.jpa.bookmanager.domain;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
@NoArgsConstructor
@RequiredArgsConstructor // @NonNull이라고 적힌 변수들로만 이루어진 생성자 만들 수 있음.
public class User {
    @NonNull
    private String name;
    @NonNull
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
