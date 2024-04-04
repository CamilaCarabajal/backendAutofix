package com.example.backendAutofix.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "repairs")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RepairEntity {
    @Id
    @Column(unique = true, nullable = false)

    private Long id;
    @ManyToOne
    @JoinColumn(name = "patente")
    private VehicleEntity patente;


    private LocalDate fechaIngreso;
    private int tipoReparacion;
    private int montoReparacion;
    private LocalDate fechaSalidaReparacion;
    private LocalDate fechaClienteVehiculo;


}
