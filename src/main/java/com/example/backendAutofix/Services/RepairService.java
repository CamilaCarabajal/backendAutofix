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
import java.util.List;


@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    public RepairEntity createRepair(RepairEntity repair){
        return repairRepository.save(repair);
    }

    public int costoReparaciones (RepairEntity reparacion, VehicleEntity vehiculo){
        if(reparacion.getTipoReparacion() == 1){
            if(vehiculo.getMotor() == "Gasolina" || vehiculo.getMotor() == "Diesel"){
                return 120000;

            }
            else if(vehiculo.getMotor() == "Hibrido"){
                return 180000;
            }
            else if(vehiculo.getMotor() == "Electrico"){
                return 220000;
            }

        }
        else if(reparacion.getTipoReparacion() == 2){
            if(vehiculo.getMotor() == "Gasolina" || vehiculo.getMotor() == "Diesel"){
                return 130000;
            }
            else if(vehiculo.getMotor() == "Hibrido"){
                return 190000;
            }
            else if(vehiculo.getMotor() == "Electrico"){
                return 230000;
            }
        }
        else if(reparacion.getTipoReparacion() == 3){
            if(vehiculo.getMotor() == "Gasolina"){
                return 350000;
            }
            else if(vehiculo.getMotor() == "Diesel"){
                return 450000;
            }
            else if(vehiculo.getMotor() == "Hibrido"){
                return 700000;
            }
            else if (vehiculo.getMotor() == "Electrico"){
                return 800000;
            }
        }
        else if(reparacion.getTipoReparacion() == 4){
            if(vehiculo.getMotor() == "Gasolina" || vehiculo.getMotor() == "Diesel"){
                return 210000;
            }
            else if(vehiculo.getMotor() == "Hibrido" || vehiculo.getMotor() == "Electrico"){
                return 300000;
            }

        }
        else if(reparacion.getTipoReparacion() == 5){
            if(vehiculo.getMotor() == "Gasolina" || vehiculo.getMotor() == "Diesel"){
                return 150000;
            }
            else if(vehiculo.getMotor() == "Hibrido"){
                return 200000;
            }
            else if(vehiculo.getMotor() == "Electrico"){
                return 250000;
            }
        }
        else if(reparacion.getTipoReparacion() == 6){
            if(vehiculo.getMotor() == "Gasolina"){
                return 100000;
            }
            else if(vehiculo.getMotor() == "Diesel"){
                return 120000;
            }
            else if(vehiculo.getMotor() == "Hibrido"){
                return 450000;
            }
            else if (vehiculo.getMotor() == "Electrico"){
                return 0;
            }

        }
        else if(reparacion.getTipoReparacion() == 7){
            if(vehiculo.getMotor()=="Gasolina" || vehiculo.getMotor()=="Diesel" || vehiculo.getMotor()=="Hibrido" || vehiculo.getMotor()=="Electrico"){
                return 100000;
            }
        }
        else if(reparacion.getTipoReparacion() == 8){
            if(vehiculo.getMotor() == "Gasolina" || vehiculo.getMotor() == "Diesel"){
                return 180000;
            }
            else if(vehiculo.getMotor() == "Hibrido"){
                return 210000;
            }
            else if(vehiculo.getMotor() == "Electrico"){
                return 250000;
            }

        }
        else if(reparacion.getTipoReparacion() == 9){
            if(vehiculo.getMotor() == "Gasolina" || vehiculo.getMotor() == "Diesel"){
                return 150000;
            }
            else if(vehiculo.getMotor() == "Hibrido" || vehiculo.getMotor() == "Electrico") {
                return 180000;
            }
        }
        else if(reparacion.getTipoReparacion() == 10){
            if(vehiculo.getMotor() == "Gasolina"){
                return 130000;
            }
            else if(vehiculo.getMotor() == "Diesel"){
                return 140000;
            }
            else if(vehiculo.getMotor() == "Hibrido"){
                return 220000;
            }
            else if (vehiculo.getMotor() == "Electrico"){
                return 0;
            }

        }
        else if(reparacion.getTipoReparacion() == 11){
            if(vehiculo.getMotor()=="Gasolina" || vehiculo.getMotor()=="Diesel" || vehiculo.getMotor()=="Hibrido" || vehiculo.getMotor()=="Electrico"){
                return 80000;
            }
        }

        return 0;
    }
    public int recargoKilometraje (String recargo){
        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(recargo);
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

        return 0;
    }

}
