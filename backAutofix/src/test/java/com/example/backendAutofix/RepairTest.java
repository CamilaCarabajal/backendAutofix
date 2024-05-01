package com.example.backendAutofix;

import com.example.backendAutofix.Services.RepairService;
import com.example.backendAutofix.Services.VehicleService;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional

public class RepairTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleEntity vehicleEntity;

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;

    @Mock
    private  RepairEntity repairEntity;

    @Test
    public void testCrearReparacion() {
        RepairEntity reparacion = new RepairEntity();
        reparacion.setIdReparacion(2L);
        reparacion.setFechaIngreso(LocalDate.of(2024,04,20));
        reparacion.setHoraIngreso(LocalTime.of(10,45));
        reparacion.setMontoReparacion(450000);
        reparacion.setTipoReparacion(3);
        reparacion.setFechaSalidaReparacion(LocalDate.of(2024,04,29));
        reparacion.setHoraSalidaReparacion(LocalTime.of(11,10));
        reparacion.setFechaSalidaVehiculo(LocalDate.of(2024,04,29));
        reparacion.setHoraSalidaVehiculo(LocalTime.of(20,30));

        when(repairRepository.save(reparacion)).thenReturn(reparacion);
        RepairEntity nuevaReparacion = repairService.crearReparacion(reparacion);
        assertNotNull(nuevaReparacion,"La nueva reparación no debe ser nula");
        assertEquals(2L, nuevaReparacion.getIdReparacion(), "El ID de la nueva reparación debe ser 1");
        assertEquals(LocalDate.of(2024,04,20), nuevaReparacion.getFechaIngreso(), "La fecha de ingreso debe ser 2024-04-20");
        assertEquals(LocalTime.of(10,45), nuevaReparacion.getHoraIngreso(), "La hora de ingreso debe ser la 10:45");
        assertEquals(450000, nuevaReparacion.getMontoReparacion(), "El monto de reparación debe ser 450000");
        assertEquals(3,nuevaReparacion.getTipoReparacion(),"El tipo de reparacion debe ser 3");
        assertEquals(LocalDate.of(2024,04,29),nuevaReparacion.getFechaSalidaReparacion());
        assertEquals(LocalTime.of(11,10),nuevaReparacion.getHoraSalidaReparacion());
        assertEquals(LocalDate.of(2024,04,29),nuevaReparacion.getFechaSalidaVehiculo());
        assertEquals(LocalTime.of(20,30), nuevaReparacion.getHoraSalidaVehiculo());

    }

    @Test
    public void ObtenerReparacionPorId() {

        RepairEntity reparacion = new RepairEntity();
        reparacion.setIdReparacion(1L);
        reparacion.setFechaIngreso(LocalDate.now());
        reparacion.setHoraIngreso(LocalTime.now());
        reparacion.setVehiculos(new ArrayList<>());
        reparacion.setTipoReparacion(1);
        reparacion.setMontoReparacion(120000);
        reparacion.setFechaSalidaReparacion(LocalDate.now());
        reparacion.setHoraSalidaReparacion(LocalTime.now());
        reparacion.setFechaSalidaVehiculo(LocalDate.now());
        reparacion.setHoraSalidaVehiculo(LocalTime.now());


        when(repairRepository.obtenerReparacionPorId(1L)).thenReturn(reparacion);


        RepairEntity resultado = repairService.obtenerReparacionPorId(1L);
        assertEquals(reparacion, resultado);
    }

    @Test
    public void ListaReparaciones() {
        List<RepairEntity> listaReparaciones = new ArrayList<>();
        RepairEntity reparacion1 = new RepairEntity();
        reparacion1.setIdReparacion(2L);
        reparacion1.setFechaIngreso(LocalDate.now());
        reparacion1.setHoraIngreso(LocalTime.now());
        reparacion1.setVehiculos(new ArrayList<>());
        reparacion1.setTipoReparacion(1);
        reparacion1.setMontoReparacion(120000);
        reparacion1.setFechaSalidaReparacion(LocalDate.now());
        reparacion1.setHoraSalidaReparacion(LocalTime.now());
        reparacion1.setFechaSalidaVehiculo(LocalDate.now());
        reparacion1.setHoraSalidaVehiculo(LocalTime.now());
        listaReparaciones.add(reparacion1);

        when(repairRepository.findAll()).thenReturn(listaReparaciones);

        List<RepairEntity> resultado = repairService.listaReparaciones();
        assertEquals(listaReparaciones.size(), resultado.size());
        assertEquals(listaReparaciones, resultado);
    }
    @Test
    public void ActualizarReparacion() {
        // Crear un objeto RepairEntity con todos los atributos para actualizar
        RepairEntity reparacion = new RepairEntity();
        reparacion.setIdReparacion(1L);
        reparacion.setFechaIngreso(LocalDate.now());
        reparacion.setHoraIngreso(LocalTime.now());
        reparacion.setVehiculos(new ArrayList<>());
        reparacion.setTipoReparacion(1);
        reparacion.setMontoReparacion(100);
        reparacion.setFechaSalidaReparacion(LocalDate.now());
        reparacion.setHoraSalidaReparacion(LocalTime.now());
        reparacion.setFechaSalidaVehiculo(LocalDate.now());
        reparacion.setHoraSalidaVehiculo(LocalTime.now());

        when(repairRepository.obtenerReparacionPorId(1L)).thenReturn(reparacion);
        when(repairRepository.save(reparacion)).thenReturn(reparacion);
        RepairEntity resultado = repairService.actualizarReparacion(1L, reparacion);
        assertEquals(reparacion, resultado);
    }

    @Test
    public void EliminarReparacion() {
        Long idReparacion = 1L;
        repairService.eliminarReparacion(idReparacion);
        verify(repairRepository).deleteById(idReparacion);
    }

    @Test
    public void testCalcularCostoReparacion() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setMotor("Gasolina");
        RepairEntity repair = new RepairEntity();
        repair.setTipoReparacion(1);
        int costoEsperado = 120000;
        int costoCalculado = repairService.calcularCostoReparacion(vehicle, repair);

        assertEquals(costoEsperado, costoCalculado);
    }

    @Test
    public void VincularReparacionAVehiculo() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPatente("ABGH23");
        vehicle.setMotor("Gasolina");
        RepairEntity repair = new RepairEntity();
        repair.setTipoReparacion(1);
        repair.setVehiculos(new ArrayList<>());
        vehicle.setReparaciones(new ArrayList<>());
        vehicleRepository.save(vehicle);
        lenient().when(vehicleRepository.findByPatenteQuery("ABGH23")).thenReturn(vehicle);
        lenient().when(repairRepository.save(repair)).thenReturn(repair);

        repairService.vincularReparacionAVehiculo(vehicle.getPatente(), repair);

        assertTrue(repair.getVehiculos().contains(vehicle));
        assertTrue(vehicle.getReparaciones().contains(repair));
        assertEquals(1, vehicle.getCantidadReparaciones());
    }

    @Test
    public void testFindRepairByPatente() {
        String patente = "ABC123";

        RepairEntity reparacion = new RepairEntity();
        reparacion.setIdReparacion(1L);
        reparacion.setFechaIngreso(LocalDate.now());
        reparacion.setHoraIngreso(LocalTime.now());
        reparacion.setVehiculos(new ArrayList<>());
        reparacion.setTipoReparacion(1);
        reparacion.setMontoReparacion(120000);
        reparacion.setFechaSalidaReparacion(LocalDate.now());
        reparacion.setHoraSalidaReparacion(LocalTime.now());
        reparacion.setFechaSalidaVehiculo(LocalDate.now());
        reparacion.setHoraSalidaVehiculo(LocalTime.now());

        Mockito.when(repairRepository.findRepairByPatente(patente)).thenReturn(reparacion);

        RepairEntity result = repairService.findRepairByPatente(patente);

        assertEquals(reparacion, result);
    }
    @Test
    public void testListaReparacionesPorPatente() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPatente("ABCD23");
        RepairEntity repair1 = new RepairEntity();
        VehicleEntity vehicleRepair1 = new VehicleEntity();
        vehicleRepair1.setPatente("ABCD23");
        repair1.getVehiculos().add(vehicleRepair1);

        RepairEntity repair2 = new RepairEntity();
        VehicleEntity vehicleRepair2 = new VehicleEntity();
        vehicleRepair2.setPatente("XYZT89");
        repair2.getVehiculos().add(vehicleRepair2);

        List<RepairEntity> todasLasReparaciones = new ArrayList<>();
        todasLasReparaciones.add(repair1);
        todasLasReparaciones.add(repair2);

        when(repairService.listaReparaciones()).thenReturn(todasLasReparaciones);
        List<RepairEntity> reparacionesPorPatente = repairService.listaReparacionesPorPatente("ABCD23");
        assertEquals(1, reparacionesPorPatente.size());
        assertEquals(repair1, reparacionesPorPatente.get(0));
    }

    @Test
    public void testCalcularCostoTotalReparaciones() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPatente("ABCD23");
        RepairEntity repair1 = new RepairEntity();
        repair1.setMontoReparacion(120000);
        repair1.getVehiculos().add(vehicle);

        RepairEntity repair2 = new RepairEntity();
        repair2.setMontoReparacion(130000);
        VehicleEntity vehicleRepair2 = new VehicleEntity();
        vehicleRepair2.setPatente("XYZT89");
        repair2.getVehiculos().add(vehicleRepair2);

        List<RepairEntity> reparacionesPorVehiculo = new ArrayList<>();
        reparacionesPorVehiculo.add(repair1);
        reparacionesPorVehiculo.add(repair2);
        when(repairService.listaReparacionesPorPatente("ABCD23")).thenReturn(reparacionesPorVehiculo);
        double costoTotal = repairService.calcularCostoTotalReparaciones("ABCD23");
        double costoTotalEsperado = 120000;
        assertEquals(costoTotalEsperado, costoTotal, 0.0);
    }

    @Test
    public void testCalcularDescuentoReparacion() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setCantidadReparaciones(3);
        vehicle.setMotor("Gasolina");
        double descuentoReparacion = repairService.calcularDescuentoReparacion(vehicle);

        double descuentoEsperado = 0.1;

        assertEquals(descuentoEsperado, descuentoReparacion, 0.0);
    }

    @Test
    public void testCalcularDescuentoPorDia() {
        List<RepairEntity> listaReparaciones = new ArrayList<>();
        when(repairService.listaReparacionesPorPatente("ABCD23")).thenReturn(listaReparaciones);
        double descuentoDia = repairService.calcularDescuentoPorDia("ABCD23");
        if (!listaReparaciones.isEmpty()) {
            LocalDate diaIngreso = listaReparaciones.get(0).getFechaIngreso();
            LocalTime horaIngreso = listaReparaciones.get(0).getHoraIngreso();
            if ((diaIngreso.getDayOfWeek() == DayOfWeek.MONDAY || diaIngreso.getDayOfWeek() == DayOfWeek.THURSDAY) &&
                    horaIngreso.isAfter(LocalTime.of(9, 0)) && horaIngreso.isBefore(LocalTime.of(12, 0))) {
                assertEquals(0.1, descuentoDia, 0.0);
            } else {
                assertEquals(0.0, descuentoDia, 0.0);
            }
        } else {
            assertEquals(0.0, descuentoDia, 0.0);
        }
    }

    @Test
    public void testCalcularDescuentoBono() {
        when(vehicleEntity.getMarca()).thenReturn("Toyota");

        double descuentoCalculado = repairService.calcularDescuentoBono(vehicleEntity);
        assertEquals(70000.0, descuentoCalculado, 0.0);
    }

    @Test
    public void testCalcularRecargoKilometraje() {
        when(vehicleEntity.getKilometraje()).thenReturn(10000);
        when(vehicleEntity.getTipoModelo()).thenReturn("SUV");

        double recargoCalculado = repairService.calcularRecargoKilometraje(vehicleEntity);

        assertEquals(0.05, recargoCalculado, 0.0);
    }

    @Test
    public void testCalculoRecargoAntiguedad() {
        when(vehicleEntity.getAno()).thenReturn(2018);
        when(vehicleEntity.getTipoModelo()).thenReturn("SUV");

        double recargoCalculado = repairService.calculoRecargoAntiguedad(vehicleEntity);
        assertEquals(0.07, recargoCalculado, 0.0);
    }

    @Test
    public void testCalculoRecargoDia() {
        // Crear una instancia de RepairEntity con las fechas de salida necesarias
        RepairEntity repairEntity = new RepairEntity();
        repairEntity.setFechaSalidaReparacion(LocalDate.of(2023, 5, 10));
        repairEntity.setFechaSalidaVehiculo(LocalDate.of(2023, 5, 10));
        List<RepairEntity> listaReparaciones = new ArrayList<>();
        listaReparaciones.add(repairEntity);
        when(repairService.listaReparacionesPorPatente("ABCD23")).thenReturn(listaReparaciones);
        double recargoCalculado = repairService.calculoRecargoDia("ABCD23");
        double recargoEsperado = 0.0;
        assertEquals(recargoEsperado, recargoCalculado);
    }













}
