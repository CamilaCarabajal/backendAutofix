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
}
