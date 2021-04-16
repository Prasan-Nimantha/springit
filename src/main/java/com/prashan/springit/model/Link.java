package com.prashan.springit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue
    private Long id;
    private String tittle;
    private String url;
}
