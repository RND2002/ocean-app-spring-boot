package com.udemyapi.udemyclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course extends BaseClass {

    private String title;

    private String description;

    private String tags;
    @Lob
    private byte[] image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_courses",
            joinColumns = {
                    @JoinColumn(name = "course_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private List<User> users;
    @JsonIgnore

    @OneToMany(mappedBy = "course")
    private List<Section> sections;


}
