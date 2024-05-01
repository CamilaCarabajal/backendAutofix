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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class VehicleTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;

    @Test
    public void FindByPatenteQuery() {
        VehicleEntity vehicle = new VehicleEntity("ABCD123", "Toyota", "Sedan", 2020, "Gasolina", 5, 50000, 2,null,null);

        when(vehicleService.obtenerVehiculoPatente("ABCD123")).thenReturn(vehicle);

        VehicleEntity result = vehicleService.obtenerVehiculoPatente("ABCD123");

        // Verificaciones
        assertEquals("ABCD123", result.getPatente());
        assertEquals("Toyota", result.getMarca());
        assertEquals("Sedan", result.getTipoModelo());
        assertEquals(2020, result.getAno());
        assertEquals("Gasolina", result.getMotor());
        assertEquals(5, result.getAsientos());
        assertEquals(50000, result.getKilometraje());
        assertEquals(2, result.getCantidadReparaciones());
    }

    @Test
    public void registroVehiculo(){
        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setPatente("ABGH23");
        vehicle1.setMarca("Honda");
        vehicle1.setTipoModelo("Sedan");
        vehicle1.setAno(2010);
        vehicle1.setMotor("Diesel");
        vehicle1.setAsientos(5);
        vehicle1.setKilometraje(10000);
        vehicle1.setCantidadReparaciones(0);
        when(vehicleRepository.save(Mockito.any(VehicleEntity.class))).thenReturn(vehicle1);
        VehicleEntity resultado = vehicleService.registroVehiculo(vehicle1);
        assertEquals(resultado.getPatente(), vehicle1.getPatente());

    }
    @Test
    public void testExistsByPatenteQuery() {
        // Arrange
        String patente = "ABCD123";
        when(vehicleRepository.existsByPatenteQuery(patente)).thenReturn(true);

        // Act
        boolean result = vehicleRepository.existsByPatenteQuery(patente);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void ListaVehiculos() {
        // Datos de prueba
        List<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(new VehicleEntity("ABCD123", "Toyota", "Sedan", 2020, "Gasolina", 5, 50000, 2,null,null));
        vehicles.add(new VehicleEntity("DEFD456", "Honda", "SUV", 2018, "Diesel", 7, 70000, 1,null,null));


        when(vehicleService.listaVehiculos()).thenReturn(vehicles);

        // Llama al método para obtener la lista de vehículos
        List<VehicleEntity> resultList = vehicleService.listaVehiculos();

        // Verificación de la lista de vehículos
        assertEquals(2, resultList.size()); // Verifica que se devuelvan 2 vehículos en la lista
        assertEquals("Toyota", resultList.get(0).getMarca()); // Verifica la marca del primer vehículo
        assertEquals("Honda", resultList.get(1).getMarca()); // Verifica la marca del segundo vehículo
    }

    @Test
    public void ModificarVehiculo() {
        // Datos de prueba
        VehicleEntity existingVehicle = new VehicleEntity("ABCF123", "Toyota", "Sedan", 2020, "Gasolina", 5, 50000, 2,null,null);
        VehicleEntity updatedVehicle = new VehicleEntity("AKUP123", "Toyota", "SUV", 2021, "Gasolina", 5, 60000, 3,null,null);

        when(vehicleRepository.findByPatenteQuery("ABCF123")).thenReturn(existingVehicle);
        when(vehicleRepository.save(updatedVehicle)).thenReturn(updatedVehicle);

        VehicleEntity result = vehicleService.modificarVehiculo("ABCF123", updatedVehicle);

        // Verificación
        assertNotNull(result); // Verifica que se devuelva un vehículo modificado
        assertEquals("SUV", result.getTipoModelo()); // Verifica que el tipo de modelo se haya actualizado a SUV
        assertEquals(2021, result.getAno()); // Verifica que el año se haya actualizado a 2021
        assertEquals(60000, result.getKilometraje()); // Verifica que el kilometraje se haya actualizado a 60000
        assertEquals(3, result.getCantidadReparaciones()); // Verifica que la cantidad de reparaciones se haya actualizado a 3
    }

    @Test
    public void EliminarVehiculo() {
        // Arrange
        String patente = "ABCF123";
        when(vehicleRepository.existsByPatenteQuery(patente)).thenReturn(true);

        // Act
        boolean result = vehicleService.eliminarVehiculo(patente);

        // Assert
        assertEquals(true, result);
        verify(vehicleRepository).deleteByPatenteQuery(patente);
    }

    @Test
    public void EliminarVehiculo_NoExist() {
        // Arrange
        String patente = "XYZ789";
        when(vehicleRepository.existsByPatenteQuery(patente)).thenReturn(false);

        // Act
        boolean result = vehicleService.eliminarVehiculo(patente);

        // Assert
        assertEquals(false, result);
        verify(vehicleRepository, never()).deleteByPatenteQuery(patente);
    }









}
