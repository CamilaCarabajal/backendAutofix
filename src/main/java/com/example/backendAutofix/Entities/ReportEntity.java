package com.example.backendAutofix.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReporte")
public class ReportEntity {

    @Id
    @Column(unique = true, nullable = false)
    private long idReporte;

    private double descuentos;
    private double recargos;
    private double iva;
    private double costoTotal;

    @ManyToOne
    @JoinColumn(name = "patente", referencedColumnName = "patente")
    @JsonIgnore
    private VehicleEntity vehiculo;



}
