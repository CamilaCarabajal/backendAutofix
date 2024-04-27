package com.example.backendAutofix.Entities;

import com.example.backendAutofix.Entities.RepairEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "patente")
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
    @JsonIgnore
    List<RepairEntity> reparaciones;
}
