package com.udemyapi.udemyclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lecture extends BaseClass{

    private String name;


    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore
    private Section section;

   @JsonManagedReference
    @OneToOne
    private Resource resource;
}
