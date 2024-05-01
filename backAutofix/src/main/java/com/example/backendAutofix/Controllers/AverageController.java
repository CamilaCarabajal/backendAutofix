package com.example.backendAutofix.Controllers;
import com.example.backendAutofix.Services.RepairService;
import com.example.backendAutofix.Services.VehicleService;
import com.example.backendAutofix.Entities.AverageEntity;
import com.example.backendAutofix.Services.AverageService;
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
public class AverageController {

    @Autowired
    RepairService repairService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    AverageService averageService;

    @GetMapping("/averages")
    public ResponseEntity<List<AverageEntity>> calcularTiempoPromedioReparacionPorMarca() {
        List<AverageEntity> promedioOrdenado = averageService.calcularTiempoPromedioReparacionPorMarca();
        return ResponseEntity.ok(promedioOrdenado);
    }
}
