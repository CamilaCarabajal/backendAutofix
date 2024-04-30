package com.example.backendAutofix.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "average")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AverageEntity {
    @Id
    @Column(unique = true, nullable = false)
    private Long idPromedio;
    private String MarcaVehiculo;
    private double promedioTiempoVehiculo;

}
