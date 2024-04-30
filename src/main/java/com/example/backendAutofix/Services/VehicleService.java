package com.example.backendAutofix.Services;

import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Repositories.VehicleRepository;
import com.example.backendAutofix.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    RepairRepository repairRepository;

    public VehicleEntity registroVehiculo(VehicleEntity vehicle){
        return vehicleRepository.save(vehicle);
    }

    public List<VehicleEntity> listaVehiculos(){
        return vehicleRepository.findAll();
    }

    public VehicleEntity obtenerVehiculoPatente(String patente){
        return vehicleRepository.findByPatenteQuery(patente);
    }

    public VehicleEntity modificarVehiculo(String patente, VehicleEntity vehiculo){
        VehicleEntity vehiculoExistente = vehicleRepository.findByPatenteQuery(patente);
        if(vehiculoExistente != null){
            vehiculo.setPatente(patente);
            return vehicleRepository.save(vehiculo);
        }
        return null;
    }

    public boolean eliminarVehiculo(String patente){
        if(vehicleRepository.existsByPatenteQuery(patente)){
            vehicleRepository.deleteByPatenteQuery(patente);
            return true;
        }
        return false;
    }



   /* public List<String> obtenerTiposReparacionesPorVehiculo(String patente) {
        return vehicleRepository.findTiposReparacionesByVehiculoId(patente);
    }*/






}
