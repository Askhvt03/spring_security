package com.askhvt.springsecurity.repository;

import com.askhvt.springsecurity.entity.Assembly;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
@Transactional
public interface AssemblyRepository extends JpaRepository<Assembly, Long> {
}
