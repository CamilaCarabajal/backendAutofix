package com.example.backendAutofix.Repositories;
import com.example.backendAutofix.Entities.RepairEntity;
import com.example.backendAutofix.Entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    @Query(value = "SELECT * FROM vehicles WHERE vehicles.patente = :patente", nativeQuery = true)
    VehicleEntity findByPatente(@Param("patente") String patente);
}
