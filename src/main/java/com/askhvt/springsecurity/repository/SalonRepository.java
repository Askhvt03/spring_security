package com.askhvt.springsecurity.repository;

import com.askhvt.springsecurity.entity.SalonAvto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SalonRepository extends JpaRepository<SalonAvto, Long> {


}
