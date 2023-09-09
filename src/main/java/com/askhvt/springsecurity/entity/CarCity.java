package com.askhvt.springsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "car_city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_name")
    private String name;

    @Column(name = "car_year")
    private int year;

    @Column(name = "country")
    private String country;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    private SalonAvto salonAvto;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Assembly> assemblies;


}
