package com.example.jpa.bookmanager.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookNameAndCategory {
    private String name;
    private String category;
    /*밑에 친구들은 interface 로 구현했을 경우*/
//    String getName();
//    String getCategory();
}
