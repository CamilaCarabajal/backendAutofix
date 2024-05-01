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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")

public class RepairController {

    @Autowired
    RepairService repairService;
    @Autowired
    VehicleService vehicleService;



    @GetMapping("/reparaciones")
    public List<RepairEntity> listaReparaciones(){
        return repairService.listaReparaciones();
    }

    @GetMapping("reparaciones/{idReparacion}")
    public ResponseEntity<RepairEntity> obtenerReparacionPorId(@PathVariable Long idReparacion) {
        RepairEntity reparacion = repairService.obtenerReparacionPorId(idReparacion);
        if (reparacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reparacion);
    }



    @PostMapping("reparaciones/crear?patente={patente}")
    public ResponseEntity<RepairEntity> crearReparacion(@RequestParam String patente, @RequestBody RepairEntity reparacion) {
        RepairEntity nuevaReparacion = repairService.crearReparacion(reparacion);
        if (nuevaReparacion != null) {
            return new ResponseEntity<>(nuevaReparacion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("reparaciones/actualizar/{idReparacion}")
    public ResponseEntity<RepairEntity> actualizarReparacion(@PathVariable Long idReparacion, @RequestBody RepairEntity reparacion) {
        RepairEntity reparacionActualizada = repairService.actualizarReparacion(idReparacion, reparacion);
        if (reparacionActualizada != null) {
            return ResponseEntity.ok(reparacionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("reparaciones/eliminar/{id}")
    public ResponseEntity<Void> eliminarReparacion(@PathVariable Long idReparacion) {
        repairService.eliminarReparacion(idReparacion);
        return ResponseEntity.noContent().build();
    }


}
