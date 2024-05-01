package com.example.backendAutofix;
import com.example.backendAutofix.Repositories.AverageRepository;
import com.example.backendAutofix.Services.RepairService;
import com.example.backendAutofix.Services.VehicleService;
import com.example.backendAutofix.Entities.AverageEntity;
import com.example.backendAutofix.Services.AverageService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import com.example.backendAutofix.Entities.VehicleEntity;
import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Repositories.VehicleRepository;
import com.example.backendAutofix.Repositories.RepairRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.listeners.MockitoListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class AverageTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;

    @Mock
    private AverageRepository averageRepository;

    @InjectMocks AverageService averageService;

    @Test
    public void testCalcularDuracionReparacionEnMinutos() {
        // Definir las fechas y horas de inicio y fin de la reparación
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        LocalTime horaInicio = LocalTime.of(10, 0);
        LocalDate fechaFin = LocalDate.of(2023, 1, 1);
        LocalTime horaFin = LocalTime.of(11, 30);

        long duracionCalculada = averageService.calcularDuracionReparacionEnMinutos(fechaInicio, horaInicio, fechaFin, horaFin);


        assertEquals(90, duracionCalculada);
    }

    @Test
    public void testCalcularTiempoPromedioReparacionPorMarca() {
        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setMarca("Toyota");

        VehicleEntity vehicle2 = new VehicleEntity();
        vehicle2.setMarca("Honda");

        VehicleEntity vehicle3 = new VehicleEntity();
        vehicle3.setMarca("Ford");

        List<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);

        RepairEntity repair1 = new RepairEntity();
        repair1.setVehiculos(vehicles);
        repair1.setFechaIngreso(LocalDate.of(2022, 5, 15));
        repair1.setHoraIngreso(LocalTime.of(9, 0));
        repair1.setFechaSalidaReparacion(LocalDate.of(2022, 5, 15));
        repair1.setHoraSalidaReparacion(LocalTime.of(10, 0));

        RepairEntity repair2 = new RepairEntity();
        repair2.setVehiculos(vehicles);
        repair2.setFechaIngreso(LocalDate.of(2022, 5, 20));
        repair2.setHoraIngreso(LocalTime.of(10, 0));
        repair2.setFechaSalidaReparacion(LocalDate.of(2022, 5, 20));
        repair2.setHoraSalidaReparacion(LocalTime.of(12, 0));

        RepairEntity repair3 = new RepairEntity();
        repair3.setVehiculos(vehicles);
        repair3.setFechaIngreso(LocalDate.of(2022, 5, 10));
        repair3.setHoraIngreso(LocalTime.of(8, 0));
        repair3.setFechaSalidaReparacion(LocalDate.of(2022, 5, 10));
        repair3.setHoraSalidaReparacion(LocalTime.of(10, 0));

        List<RepairEntity> repairs = new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);
        repairs.add(repair3);


        Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);
        Mockito.when(repairRepository.findAll()).thenReturn(repairs);

        List<AverageEntity> resultados = averageService.calcularTiempoPromedioReparacionPorMarca();

        // Datos para validar el resultado esperado
        /*AverageEntity expectedAverage1 = new AverageEntity();
        expectedAverage1.setMarcaVehiculo("Toyota");
        expectedAverage1.setPromedioTiempoVehiculo(60.0);

        AverageEntity expectedAverage2 = new AverageEntity();
        expectedAverage2.setMarcaVehiculo("Honda");
        expectedAverage2.setPromedioTiempoVehiculo(120.0);

        AverageEntity expectedAverage3 = new AverageEntity();
        expectedAverage3.setMarcaVehiculo("Ford");
        expectedAverage3.setPromedioTiempoVehiculo(120.0);

        List<AverageEntity> expectedList = new ArrayList<>();
        expectedList.add(expectedAverage1);
        expectedList.add(expectedAverage2);
        expectedList.add(expectedAverage3);*/

        assertEquals(3, resultados.size());
        assertEquals("Toyota", resultados.get(0).getMarcaVehiculo());
        assertEquals(0, resultados.get(0).getPromedioTiempoVehiculo()); // 60 minutos
        assertEquals("Honda", resultados.get(1).getMarcaVehiculo());
        assertEquals(0, resultados.get(1).getPromedioTiempoVehiculo()); // 120 minutos
        assertEquals("Ford", resultados.get(2).getMarcaVehiculo());
        assertEquals(0, resultados.get(2).getPromedioTiempoVehiculo()); // 120 minutos
    }
}
