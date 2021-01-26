package com.niit.edu.imagegallery.models;

import lombok.Data;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "path")
    String path;
}
