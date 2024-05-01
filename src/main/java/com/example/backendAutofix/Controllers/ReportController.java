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
import com.example.backendAutofix.Entities.ReportEntity;
import com.example.backendAutofix.Services.ReportService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ReportController {

    @Autowired
    RepairService repairService;
    @Autowired
    VehicleService vehicleService;

    @Autowired
    ReportService reportService;

    @PostMapping("/generar/{patente}")
    public ResponseEntity<List<ReportEntity>> generarReportesPorVehiculoRegistrado(@PathVariable String patente, @RequestBody ReportEntity reporte) {
        List<ReportEntity> listaReportes = reportService.generarReportesPorVehiculoRegistrado(patente, reporte);

        if (listaReportes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(listaReportes, HttpStatus.OK);
        }
    }



}
