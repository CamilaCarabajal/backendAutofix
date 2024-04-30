package com.example.backendAutofix.Repositories;
import com.example.backendAutofix.Entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.backendAutofix.Entities.AverageEntity;

import java.util.List;


@Repository

public interface AverageRepository extends JpaRepository<AverageEntity, Long>{

}
