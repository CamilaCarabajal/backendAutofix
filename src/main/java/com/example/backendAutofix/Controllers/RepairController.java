package com.example.backendAutofix.Controllers;

import com.example.backendAutofix.Services.RepairService;
import com.example.backendAutofix.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Entities.RepairEntity;

@Controller
@RequestMapping("/api")

public class RepairController {

    @Autowired
    RepairService repairService;
    @Autowired
    VehicleService vehicleService;

   /*("/agregarReparacionAVehiculo")
   @PostMapping("/{idReparacion}/vehiculos/{patenteVehiculo}")
   public ResponseEntity<String> agregarReparacionAVehiculo(@PathVariable Long idReparacion, @PathVariable String patente) {
       VehicleEntity vehiculo = vehicleService.obtenerVehiculoPatente(patente);
       if (vehiculo == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado");
       }

       repairService.agregarReparacionAVehiculo(idReparacion, vehiculo);
       return ResponseEntity.ok("Reparación agregada al vehículo con éxito.");
   }*/
}
