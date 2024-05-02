package com.example.backendAutofix.Services;
import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Entities.ReportEntity;
import com.example.backendAutofix.Repositories.ReportRepository;
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

public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private RepairService repairService;

    public List<ReportEntity> generarReportesPorVehiculoRegistrado(String patente, ReportEntity reporte) {
        List<ReportEntity> listaReportes = new ArrayList<>();
        VehicleEntity vehiculo = vehicleRepository.findByPatenteQuery(patente);

        // Obtener una lista de reparaciones para el vehículo con la patente especificada
        List<RepairEntity> reparaciones = repairService.listaReparacionesPorPatente(patente);

        // Para cada reparación, generar un reporte y guardarlo en la lista
        for (RepairEntity reparacion : reparaciones) {
            reporte.setVehiculo(vehiculo);
            reporte.setDescuentos(repairService.calculoDescuentoTotal(patente));
            reporte.setRecargos(repairService.calculoRecargoTotal(patente));
            reporte.setIva(repairService.ivaCostoTotal(patente));
            reporte.setCostoTotal(repairService.calcularCostoTotalReparaciones(patente));

            // Guardar el reporte en la base de datos
            reportRepository.save(reporte);

            listaReportes.add(reporte);
        }

        return listaReportes;
    }


}
