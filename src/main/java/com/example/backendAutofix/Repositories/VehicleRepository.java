package com.example.backendAutofix.Repositories;
import com.example.backendAutofix.Entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    @Query(value = "SELECT * FROM vehicles WHERE vehicles.patente = :patente", nativeQuery = true)
    VehicleEntity findByPatenteQuery(@Param("patente") String patente);



    @Query("SELECT DISTINCT r.tipoReparacion FROM VehicleEntity v JOIN v.reparaciones r WHERE v.patente = :patente")
    List<String> findTiposReparacionesByVehiculoId(Long patente);







}
