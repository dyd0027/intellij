package com.example.jpa.bookmanager.domain.listener;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime localDateTime);
    void setUpdatedAt(LocalDateTime localDateTime);


}
