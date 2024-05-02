package com.example.backendAutofix.Services;

import com.example.backendAutofix.Entities.AverageEntity;
import com.example.backendAutofix.Repositories.AverageRepository;
import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Repositories.VehicleRepository;
import com.example.backendAutofix.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class AverageService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public long calcularDuracionReparacionEnMinutos(LocalDate fechaIngreso, LocalTime horaIngreso, LocalDate fechaSalida, LocalTime horaSalida) {
        LocalDateTime inicioReparacion = LocalDateTime.of(fechaIngreso, horaIngreso);
        LocalDateTime finReparacion = LocalDateTime.of(fechaSalida, horaSalida);

        Duration duracionReparacion = Duration.between(inicioReparacion, finReparacion);

        long duracionEnMinutos = duracionReparacion.toMinutes();

        return duracionEnMinutos;
    }


    public List<AverageEntity> calcularTiempoPromedioReparacionPorMarca() {
        List<VehicleEntity> vehicles = vehicleRepository.findAll();
        List<RepairEntity> repairs = repairRepository.findAll();

        List<AverageEntity> averageList = new ArrayList<>();

        for (VehicleEntity vehicle : vehicles) {
            List<RepairEntity> reparacionesMarca = repairs.stream()
                    .filter(reparacion -> reparacion.getVehiculos().equals(vehicle))
                    .collect(Collectors.toList());

            double tiempoPromedio = reparacionesMarca.stream()
                    .mapToLong(reparacion -> calcularDuracionReparacionEnMinutos(reparacion.getFechaIngreso(), reparacion.getHoraIngreso(), reparacion.getFechaSalidaReparacion(), reparacion.getHoraSalidaReparacion()))
                    .average()
                    .orElse(0.0);

            AverageEntity averageEntity = new AverageEntity();
            averageEntity.setMarcaVehiculo(vehicle.getMarca());
            averageEntity.setPromedioTiempoVehiculo(tiempoPromedio);

            averageList.add(averageEntity);
        }

        averageList.sort(Comparator.comparingDouble(AverageEntity::getPromedioTiempoVehiculo));

        return averageList;
    }
}
