package com.ledlampapi.repository;

import com.ledlampapi.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LampRepository extends JpaRepository<Color, Long> {


}
