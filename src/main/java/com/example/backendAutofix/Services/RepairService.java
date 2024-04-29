package com.example.backendAutofix.Services;

import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Repositories.VehicleRepository;
import com.example.backendAutofix.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleRepository vehicleRepository;


    public RepairEntity obtenerReparacionPorId(Long idReparacion) {
        return repairRepository.obtenerReparacionPorId(idReparacion);
    }

    public List<RepairEntity> listaReparaciones(){
        return repairRepository.findAll();
    }


    public RepairEntity actualizarReparacion(Long idReparacion, RepairEntity reparacion){
        RepairEntity reparacionExistente = repairRepository.obtenerReparacionPorId(idReparacion);
        if(reparacionExistente != null){
            reparacion.setIdReparacion(idReparacion);
            return repairRepository.save(reparacion);

        }
        return null;
    }

    public void eliminarReparacion(Long idReparacion){
        repairRepository.deleteById(idReparacion);
    }

    public int calcularCostoReparacion(VehicleEntity vehiculo, RepairEntity reparacion){
        int costoReparacion = 0;
        String tipoMotor = vehiculo.getMotor();
        int tipoReparacion = reparacion.getTipoReparacion();

        if(tipoMotor.equals("Gasolina")){
            if(tipoReparacion == 1){
                costoReparacion = 120000;
            } else if (tipoReparacion == 2) {
                costoReparacion =130000;

            } else if (tipoReparacion == 3) {
                costoReparacion = 350000;
            } else if (tipoReparacion == 4) {
                costoReparacion = 210000;

            } else if (tipoReparacion == 5) {
                costoReparacion = 150000;

            } else if (tipoReparacion == 6) {
                costoReparacion = 100000;

            } else if (tipoReparacion == 7) {
                costoReparacion = 100000;

            } else if (tipoReparacion == 8) {
                costoReparacion = 180000;

            } else if (tipoReparacion == 9) {
                costoReparacion = 150000;

            } else if (tipoReparacion == 10) {
                costoReparacion = 130000;

            } else {
                costoReparacion = 80000;

            }

        } else if (tipoMotor.equals("Diesel")) {
            if(tipoReparacion == 1){
                costoReparacion = 120000;
            } else if (tipoReparacion == 2) {
                costoReparacion =130000;

            } else if (tipoReparacion == 3) {
                costoReparacion = 450000;
            } else if (tipoReparacion == 4) {
                costoReparacion = 210000;

            } else if (tipoReparacion == 5) {
                costoReparacion = 150000;

            } else if (tipoReparacion == 6) {
                costoReparacion = 120000;

            } else if (tipoReparacion == 7) {
                costoReparacion = 100000;

            } else if (tipoReparacion == 8) {
                costoReparacion = 180000;

            } else if (tipoReparacion == 9) {
                costoReparacion = 150000;

            } else if (tipoReparacion == 10) {
                costoReparacion = 140000;

            } else {
                costoReparacion = 80000;

            }
            
        } else if (tipoMotor.equals("Hibrido")) {
            if(tipoReparacion == 1){
                costoReparacion = 180000;
            } else if (tipoReparacion == 2) {
                costoReparacion =190000;

            } else if (tipoReparacion == 3) {
                costoReparacion = 700000;
            } else if (tipoReparacion == 4) {
                costoReparacion = 300000;

            } else if (tipoReparacion == 5) {
                costoReparacion = 200000;

            } else if (tipoReparacion == 6) {
                costoReparacion = 450000;

            } else if (tipoReparacion == 7) {
                costoReparacion = 100000;

            } else if (tipoReparacion == 8) {
                costoReparacion = 210000;

            } else if (tipoReparacion == 9) {
                costoReparacion = 180000;

            } else if (tipoReparacion == 10) {
                costoReparacion = 220000;

            } else {
                costoReparacion = 80000;

            }
            
        } else if (tipoMotor.equals("Electrico")) {
            if(tipoReparacion == 1){
                costoReparacion = 220000;
            } else if (tipoReparacion == 2) {
                costoReparacion =230000;

            } else if (tipoReparacion == 3) {
                costoReparacion = 800000;
            } else if (tipoReparacion == 4) {
                costoReparacion = 300000;

            } else if (tipoReparacion == 5) {
                costoReparacion = 250000;

            } else if (tipoReparacion == 6) {
                costoReparacion = 0;

            } else if (tipoReparacion == 7) {
                costoReparacion = 100000;

            } else if (tipoReparacion == 8) {
                costoReparacion = 250000;

            } else if (tipoReparacion == 9) {
                costoReparacion = 180000;

            } else if (tipoReparacion == 10) {
                costoReparacion = 0;

            } else {
                costoReparacion = 80000;

            }
            
        }
        return costoReparacion;
    }

    public RepairEntity crearReparacion(String patente, RepairEntity reparacion){

        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(patente);
        if(vehiculo != null){
            int costoReparacion = calcularCostoReparacion(vehiculo,reparacion);
            reparacion.getVehiculos().add(vehiculo);
            vehiculo.getReparaciones().add(reparacion);
            reparacion.setMontoReparacion(costoReparacion);
            vehiculo.setCantidadReparaciones(vehiculo.getCantidadReparaciones()+1);
            return repairRepository.save(reparacion);
        }
        return null;
    }

    public List<RepairEntity> listaReparacionesPorPatente(String patente) {
        List<RepairEntity> reparacionesPorVehiculo = new ArrayList<>();

        List<RepairEntity> todasLasReparaciones = listaReparaciones();

        for (RepairEntity reparacion : todasLasReparaciones) {
            for (VehicleEntity vehiculo : reparacion.getVehiculos()) {
                if (vehiculo.getPatente().equals(patente)) {
                    reparacionesPorVehiculo.add(reparacion);
                }
            }
        }

        return reparacionesPorVehiculo;
    }

    public double calcularCostoTotalReparaciones(String patente) {
        double costoTotal = 0.0;

        List<RepairEntity> reparacionesPorVehiculo = listaReparacionesPorPatente(patente);

        for (RepairEntity reparacion : reparacionesPorVehiculo) {
            costoTotal += reparacion.getMontoReparacion();
        }

        return costoTotal;
    }
/*-------------------------------------------------------------------------------------------*/
    /*DESCUENTOS*/
    public double calcularDescuentoReparacion(VehicleEntity vehiculo){
        double descuentoReparacion = 0.0;
        int cantReparaciones = vehiculo.getCantidadReparaciones();
        String tipoMotor = vehiculo.getMotor();

        if(tipoMotor == "Gasolina") {
            if(cantReparaciones>=1 && cantReparaciones<=2){
                descuentoReparacion = 0.05;
            } else if (cantReparaciones>=3 && cantReparaciones<=5) {
                descuentoReparacion = 0.1;
            } else if (cantReparaciones>=6 && cantReparaciones<=9) {
                descuentoReparacion = 0.15;
            } else if (cantReparaciones>=10) {
                descuentoReparacion = 0.2;
            }
        } else if (tipoMotor == "Diesel") {
            if(cantReparaciones>=1 && cantReparaciones<=2){
                descuentoReparacion = 0.07;
            } else if (cantReparaciones>=3 && cantReparaciones<=5) {
                descuentoReparacion = 0.12;
            } else if (cantReparaciones>=6 && cantReparaciones<=9) {
                descuentoReparacion = 0.17;
            } else if (cantReparaciones>=10) {
                descuentoReparacion = 0.22;
            }

        } else if (tipoMotor == "Hibrido") {
            if(cantReparaciones>=1 && cantReparaciones<=2){
                descuentoReparacion = 0.1;
            } else if (cantReparaciones>=3 && cantReparaciones<=5) {
                descuentoReparacion = 0.15;
            } else if (cantReparaciones>=6 && cantReparaciones<=9) {
                descuentoReparacion = 0.2;
            } else if (cantReparaciones>=10) {
                descuentoReparacion = 0.25;
            }

        } else if(tipoMotor == "Electrico"){
            if(cantReparaciones>=1 && cantReparaciones<=2){
                descuentoReparacion = 0.08;
            } else if (cantReparaciones>=3 && cantReparaciones<=5) {
                descuentoReparacion = 0.13;
            } else if (cantReparaciones>=6 && cantReparaciones<=9) {
                descuentoReparacion = 0.18;
            } else if (cantReparaciones>=10) {
                descuentoReparacion = 0.23;
            }
        }
        return descuentoReparacion;


    }

    public double descuentoPorDia(RepairEntity reparacion){
        double descuentoDia = 0.0;
        LocalDate diaIngreso = reparacion.getFechaIngreso();
        LocalTime horaIngreso = reparacion.getHoraIngreso();
        if ((diaIngreso.getDayOfWeek() == DayOfWeek.MONDAY) || (diaIngreso.getDayOfWeek() == DayOfWeek.THURSDAY)) {
            if (horaIngreso.isAfter(LocalTime.of(9, 0)) && horaIngreso.isBefore(LocalTime.of(12, 0))) {
                descuentoDia = 0.1;
            }
        }
        return descuentoDia;

    }

    //public double descuentoBono();




    /*-------------------------------------------------------------------------------------------*/
    /*RECARGOS */

    public double recargoKilometraje








}
