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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "repairs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReparacion")
public class RepairEntity {
    @Id
    @Column(unique = true, nullable = false)
    private Long idReparacion;

    @ManyToMany(mappedBy = "reparaciones")
    @JsonIgnore
    List<VehicleEntity> vehiculos = new ArrayList<>();
    private int tipoReparacion;
    private int montoReparacion;

}
