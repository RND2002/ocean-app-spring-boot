package com.udemyapi.udemyclone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lecture extends BaseClass{

    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @OneToOne
    private Resource resource;
}
