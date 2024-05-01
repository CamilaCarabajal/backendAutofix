package com.example.backendAutofix.Controllers;
import com.example.backendAutofix.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.backendAutofix.Entities.VehicleEntity;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;



    @PostMapping("/vehiculos")
    public ResponseEntity<VehicleEntity> registroVehiculo(@RequestBody VehicleEntity vehiculo) {
        VehicleEntity nuevoVehiculo = vehicleService.registroVehiculo(vehiculo);
        return ResponseEntity.ok(nuevoVehiculo);
    }

    @GetMapping("/vehiculos/{patente}")
    public ResponseEntity<VehicleEntity> obtenerVehiculoPatente(@PathVariable String patente) {
        VehicleEntity vehiculo = vehicleService.obtenerVehiculoPatente(patente);
        if (vehiculo != null) {
            return ResponseEntity.ok(vehiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/vehiculos/{patente}")
    public ResponseEntity<VehicleEntity> modificarVehiculo(@PathVariable String patente, @RequestBody VehicleEntity vehiculo) {
        VehicleEntity vehiculoActualizado = vehicleService.modificarVehiculo(patente, vehiculo);
        if (vehiculoActualizado != null) {
            return ResponseEntity.ok(vehiculoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/vehiculos/{patente}")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable String patente) {
        boolean eliminado = vehicleService.eliminarVehiculo(patente);
        if (eliminado) {
            return ResponseEntity.ok("Vehículo ha sido eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }








}
