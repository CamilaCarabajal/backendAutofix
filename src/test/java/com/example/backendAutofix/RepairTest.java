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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional

public class RepairTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;

    /*@Test
    public void agregarReparacionAVehiculo(){
        RepairEntity reparacion = new RepairEntity();
        reparacion.setIdReparacion(1L);
        reparacion.setTipoReparacion(10);
        reparacion.setMontoReparacion(130000);

        VehicleEntity vehiculo = new VehicleEntity();
        vehiculo.setPatente("DHSJ23");
        vehiculo.setMarca("Toyota");
        vehiculo.setTipoModelo("SUV");
        vehiculo.setAno(2014);
        vehiculo.setMotor("Gasolina");
        vehiculo.setAsientos(5);
        vehiculo.setKilometraje(20000);
        vehiculo.setCantidadReparaciones(0);

        when(repairRepository.obtenerReparacionPorId(anyLong())).thenReturn(reparacion);
        repairService.agregarReparacionAVehiculo(1L,vehiculo);
        assertEquals(1, vehiculo.getCantidadReparaciones());


    }*/
}
