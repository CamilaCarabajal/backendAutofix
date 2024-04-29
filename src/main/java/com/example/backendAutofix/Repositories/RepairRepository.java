package com.example.backendAutofix.Repositories;
import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    @Query("SELECT r FROM RepairEntity r WHERE r.idReparacion = :idReparacion")
    RepairEntity obtenerReparacionPorId(@Param("id") Long idReparacion);


}
