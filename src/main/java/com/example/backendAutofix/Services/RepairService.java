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
import java.time.temporal.ChronoUnit;
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

    public RepairEntity findRepairByPatente(String patente) {
        return repairRepository.findRepairByPatente(patente);
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

    public double calcularDescuentoPorDia(String patente){

        List<RepairEntity> listaReparaciones = listaReparacionesPorPatente(patente);


        double descuentoDia = 0.0;
        LocalDate diaIngreso = listaReparaciones.get(0).getFechaIngreso();
        LocalTime horaIngreso = listaReparaciones.get(0).getHoraIngreso();
        if ((diaIngreso.getDayOfWeek() == DayOfWeek.MONDAY) || (diaIngreso.getDayOfWeek() == DayOfWeek.THURSDAY)) {
            if (horaIngreso.isAfter(LocalTime.of(9, 0)) && horaIngreso.isBefore(LocalTime.of(12, 0))) {
                descuentoDia = 0.1;
            }
        }
        return descuentoDia;

    }

    public double calcularDescuentoBono(VehicleEntity vehiculo){
        double descuentoBono = 0.0;
        String marcaVehiculo = vehiculo.getMarca();
        List<Integer> precioBono = List.of(70000,50000,30000,40000);
        List<Integer> cantBonos = new ArrayList<>(List.of(5,2,1,7));

        if(cantBonos.get(0)>=1 && marcaVehiculo == "Toyota"){
            descuentoBono = precioBono.get(0);
            cantBonos.set(0,cantBonos.get(0)-1);
        } else if (cantBonos.get(1)>=1 && marcaVehiculo == "Ford"){
            descuentoBono = precioBono.get(1);
            cantBonos.set(1,cantBonos.get(1)-1);
        }else if (cantBonos.get(2)>=1 && marcaVehiculo == "Hyundai"){
            descuentoBono = precioBono.get(2);
            cantBonos.set(2,cantBonos.get(2)-1);
        }else if (cantBonos.get(3)>=1 && marcaVehiculo == "Honda"){
            descuentoBono = precioBono.get(3);
            cantBonos.set(3,cantBonos.get(3)-1);
        }else {
            descuentoBono = 0.0;
        }
        return descuentoBono;
    }




    /*-------------------------------------------------------------------------------------------*/
    /*RECARGOS */

    public double calcularRecargoKilometraje(VehicleEntity vehiculo){
        double recargoKilometraje = 0.0;
        int kilometraje = vehiculo.getKilometraje();
        String modelo = vehiculo.getTipoModelo();

        if (kilometraje>=0 && kilometraje <=5000){
            recargoKilometraje = 0.0;
        } else if (kilometraje>=5001 && kilometraje<=12000) {
            if(modelo == "Sedan" || modelo == "Hatchback"){
                recargoKilometraje = 0.03;
            } else if (modelo == "SUV" || modelo == "Pickup" || modelo == "Furgoneta") {
                recargoKilometraje = 0.05;
            }
        } else if (kilometraje>=12001 && kilometraje<=25000) {
            if(modelo == "Sedan" || modelo == "Hatchback"){
                recargoKilometraje = 0.07;
            } else if (modelo == "SUV" || modelo == "Pickup" || modelo == "Furgoneta") {
                recargoKilometraje = 0.09;
            }

        } else if (kilometraje>=25001 && kilometraje<=40000) {
            recargoKilometraje = 0.12;

        } else if (kilometraje>=40001) {
            recargoKilometraje = 0.2;
        }
        return recargoKilometraje;
    }

    public double calculoRecargoAntiguedad(VehicleEntity vehiculo){
        double recargoAntiguedad = 0.0;
        int anoActual = 2024;
        int anoFabricacion = vehiculo.getAno();
        int antiguedad = anoActual - anoFabricacion;
        String modelo = vehiculo.getTipoModelo();

        if(antiguedad >=0 && antiguedad<=5){
            recargoAntiguedad = 0.0;
        } else if (antiguedad>=6 && antiguedad<=10) {
            if(modelo == "Sedan" || modelo == "Hatchback"){
                recargoAntiguedad = 0.05;
            } else if (modelo == "SUV" || modelo == "Pickup" || modelo == "Furgoneta") {
                recargoAntiguedad = 0.07;
            }
        } else if (antiguedad>=11 && antiguedad<=15) {
            if(modelo == "Sedan" || modelo == "Hatchback"){
                recargoAntiguedad = 0.09;
            } else if (modelo == "SUV" || modelo == "Pickup" || modelo == "Furgoneta") {
                recargoAntiguedad = 0.11;
            }
        } else if (antiguedad>=16) {
            if(modelo == "Sedan" || modelo == "Hatchback"){
                recargoAntiguedad = 0.15;
            } else if (modelo == "SUV" || modelo == "Pickup" || modelo == "Furgoneta") {
                recargoAntiguedad = 0.2;
            }
        }
        return recargoAntiguedad;
    }

    public double calculoRecargoDia(String patente){
        List<RepairEntity> listaReparaciones = listaReparacionesPorPatente(patente);
        double recargoDia = 0.0;

        //Se requiere buscar la ultima posicion de las reparaciones
        LocalDate salidaReparacion = listaReparaciones.get(listaReparaciones.size()-1).getFechaSalidaReparacion();
        LocalDate salidaCliente = listaReparaciones.get(listaReparaciones.size()-1).getFechaSalidaVehiculo();

        long cantDias = ChronoUnit.DAYS.between(salidaReparacion,salidaCliente);

        if(salidaReparacion == salidaCliente){
            recargoDia = 0.0;
        }else{
            recargoDia = cantDias*0.05;
        }
        return recargoDia;
    }
    public double calculoDescuentoTotal(String patente){
        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(patente);
        double sumaReparaciones = calcularCostoTotalReparaciones(patente);

        double descuentoReparacion = calcularDescuentoReparacion(vehiculo);
        double descuentoBono = calcularDescuentoBono(vehiculo);
        double descuentoDia = calcularDescuentoPorDia(patente);

        double descuentoTotal = (sumaReparaciones*descuentoBono + sumaReparaciones+descuentoReparacion + sumaReparaciones*descuentoDia);
        return descuentoTotal;
    }

    public double calculoRecargoTotal(String patente){
        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(patente);
        double sumaReparaciones = calcularCostoTotalReparaciones(patente);

        double recargoKilometraje = calcularRecargoKilometraje(vehiculo);
        double recargoAntiguedad = calculoRecargoAntiguedad(vehiculo);
        double recargoDia = calculoRecargoDia(patente);

        double recargoTotal = (sumaReparaciones*recargoKilometraje + sumaReparaciones*recargoAntiguedad + sumaReparaciones*recargoDia );
        return  recargoTotal;
    }

    public double ivaCostoTotal(String patente){
        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(patente);
        double sumaReparaciones = calcularCostoTotalReparaciones(patente);

        double iva = sumaReparaciones*0.19;
        return iva;
    }

    public double obtenerCostoTotalVehiculo(String patente){

        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(patente);
        double sumaReparaciones = calcularCostoTotalReparaciones(patente);

        //Descuentos
        double descuentoTotal = calculoDescuentoTotal(patente);
        //Recargos
        double recargoTotal = calculoRecargoTotal(patente);

        double iva = ivaCostoTotal(patente);


        double CostoTotalVehiculo= (sumaReparaciones - descuentoTotal + recargoTotal) + iva;

        return CostoTotalVehiculo;

    }








}
