package com.udemyapi.udemyclone.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    private  String name;
//    public Role(String role) {
//        this.role = role;
//    }

//    private final String role2 "ROLE_AUTHOR";


}
