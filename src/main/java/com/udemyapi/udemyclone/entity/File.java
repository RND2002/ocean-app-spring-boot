package com.udemyapi.udemyclone.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class File extends Resource {

    private String type;
}
