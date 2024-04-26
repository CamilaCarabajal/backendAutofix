package com.example.backendAutofix.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "repairs")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RepairEntity {
    @Id
    @Column(unique = true, nullable = false)
    private Long idReparacion;

    @ManyToMany(mappedBy = "reparaciones")
    @JsonBackReference
    private List<VehicleEntity> vehiculos;
    private int tipoReparacion;
    private int montoReparacion;

}
