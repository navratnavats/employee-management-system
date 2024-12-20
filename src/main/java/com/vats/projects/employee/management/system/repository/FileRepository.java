package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findByEmployeeId(Integer employeeId);

    @Modifying
    @Query("DELETE FROM File f WHERE f.employee.id = :employeeId")
    void deleteAllByEmployeeId(@Param("employeeId") Integer employeeId);

    @Modifying
    @Query("DELETE FROM File f WHERE f.employee.id = :employeeId AND f.fileName = :fileName")
    void deleteSingleFileByEmployeeIdAndFileName(@Param("employeeId") Integer employeeId, @Param("fileName") String fileName);

}
