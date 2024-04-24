package com.example.backendAutofix.Entities;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> patente;



    private LocalDate fechaIngreso;
    private int tipoReparacion;
    private int montoReparacion;
    private LocalDate fechaSalidaReparacion;
    private LocalDate fechaClienteVehiculo;


}
