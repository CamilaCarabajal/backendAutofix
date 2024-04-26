package com.example.backendAutofix.Repositories;
import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    @Query("SELECT r FROM RepairEntity r WHERE r.idReparacion = :idReparacion")
    RepairEntity obtenerReparacionPorId(@Param("id") Long idReparacion);

    @Query("UPDATE RepairEntity r SET r.vehiculos = :vehiculo WHERE r.idReparacion = :idReparacion")
    void agregarReparacionAVehiculo(@Param("idReparacion") Long idReparacion, @Param("vehiculo") VehicleEntity vehiculo);
}
