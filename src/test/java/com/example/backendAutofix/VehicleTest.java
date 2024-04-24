package com.example.backendAutofix;

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
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class VehicleTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void registroVehiculo(){
        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setPatente("ABCD23");
        vehicle1.setMarca("Honda");
        vehicle1.setTipoModelo("Sedan");
        vehicle1.setAño(2010);
        vehicle1.setMotor("Diesel");
        vehicle1.setAsientos(4);
        vehicle1.setKilometraje(10000);
        vehicle1.setCantidadReparaciones(0);
        Mockito.when(vehicleRepository.save(Mockito.any(VehicleEntity.class))).thenReturn(vehicle1);
        VehicleEntity resultado = vehicleService.registroVehiculo(vehicle1);
        assertEquals(resultado.getPatente(), vehicle1.getPatente());

    }
}
