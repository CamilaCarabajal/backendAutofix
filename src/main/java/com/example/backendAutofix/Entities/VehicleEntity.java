package com.example.backendAutofix.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String patente;
    private String marca;
    private String tipoModelo;
    private int año;
    private String motor;
    private int asientos;
    private int kilometraje;
    private int cantidadReparaciones;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> reparaciones;
}
