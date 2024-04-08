package com.example.backendAutofix.Services;

import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Repositories.VehicleRepository;
import com.example.backendAutofix.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public VehicleEntity registroVehiculo(VehicleEntity vehicle){
        return vehicleRepository.save(vehicle);
    }


    //Kilometraje, antiguedad



}
