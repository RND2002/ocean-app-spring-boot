package com.udemyapi.udemyclone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseClass {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(
            updatable = false
    )
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
