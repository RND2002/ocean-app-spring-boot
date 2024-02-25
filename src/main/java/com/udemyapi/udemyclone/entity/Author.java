package com.udemyapi.udemyclone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Author extends BaseClass {

    private String firstName;

    private String lastName;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    private String age;

    @ManyToMany(mappedBy = "authors",fetch = FetchType.EAGER)
    private List<Course> courses;


}
