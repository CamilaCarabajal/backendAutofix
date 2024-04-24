package com.example.backendAutofix.Controllers;
import com.example.backendAutofix.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.backendAutofix.Entities.VehicleEntity;


@Controller
@RequestMapping
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicle/new")
    public String registroVehiculoForm(Model modelo){
        VehicleEntity vehicle = new VehicleEntity();
        modelo.addAttribute("vehicle",vehicle);
        return "registro vehiculo exitoso";
    }
}
