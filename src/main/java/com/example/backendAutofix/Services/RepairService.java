package com.example.backendAutofix.Services;

import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Repositories.VehicleRepository;
import com.example.backendAutofix.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    public int
    public int recargoKilometraje (String reparacion){
        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(reparacion);
        if(vehiculo.getKilometraje()>=0 && vehiculo.getKilometraje()<5000){
            return 0;
        }
        else if ((vehiculo.getKilometraje()>=5001 && vehiculo.getKilometraje()<12000) && (vehiculo.getTipoModelo() == "Sedan" || vehiculo.getTipoModelo() == "Hatchback")) {
            return 1000;//Corregir!!!!

        }
        else if ((vehiculo.getKilometraje()>=5001 && vehiculo.getKilometraje()<12000) && (vehiculo.getTipoModelo() == "SUV" || vehiculo.getTipoModelo() == "Pickup" || vehiculo.getTipoModelo() == "Furgoneta")){
            return 1000;   //Corregir!!!!
        }
        else if ((vehiculo.getKilometraje()>=12001 && vehiculo.getKilometraje()<25000) && (vehiculo.getTipoModelo() == "Sedan" || vehiculo.getTipoModelo() == "Hatchback")){
            return 2000; //Corregir!!
        }
        else if ((vehiculo.getKilometraje()>=12001 && vehiculo.getKilometraje()<25000) && (vehiculo.getTipoModelo() == "SUV" || vehiculo.getTipoModelo() == "Pickup" || vehiculo.getTipoModelo() == "Furgoneta")){
            return 2000;   //Corregir!!!!
        }
        else if(vehiculo.getKilometraje()>=25001 && vehiculo.getKilometraje()<40000){
            return 3000;//Corregir!!!!
        }
        else if(vehiculo.getKilometraje()>=40001){
            return 4000;//Corregir!!!!
        }


    }

}
