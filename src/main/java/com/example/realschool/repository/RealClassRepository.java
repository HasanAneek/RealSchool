package com.example.realschool.repository;

import com.example.realschool.model.RealClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealClassRepository extends JpaRepository<RealClass, Integer> {
}
