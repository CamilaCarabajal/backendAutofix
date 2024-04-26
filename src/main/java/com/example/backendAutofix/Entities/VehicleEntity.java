package com.example.backendAutofix.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.backendAutofix.Entities.RepairEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private int ano;
    private String motor;
    private int asientos;
    private int kilometraje;
    private int cantidadReparaciones;

    @ManyToMany
    @JoinTable(
            name = "vehicle_repair",
            joinColumns = @JoinColumn(name = "patente"),
            inverseJoinColumns = @JoinColumn(name = "idReparacion"))
    @JsonManagedReference
    private List<RepairEntity> reparaciones;
}
