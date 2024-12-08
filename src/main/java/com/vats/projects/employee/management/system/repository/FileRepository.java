package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.dto.FileDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.beans.Transient;
import java.util.List;

public interface FileRepository extends JpaRepository<FileDTO, Integer> {
    List<FileDTO> findByEmployeeId(Integer employeeId);


    @Modifying
    @Transactional
    @Query("DELETE FROM FileDTO f where f.employee.id=:employeeId")
    void deleteAllByEmployeeId(@Param("employeeId") Integer employeeId);

    @Modifying
    @Transactional
    @Query("DELETE FROM FileDTO f where f.employee.id=:employeeId AND f.fileName=:fileName")
    void deleteSingleFileForParticularEmployee(@Param("employeeId") Integer employeeId, @Param("fileName") String fileName);
}
