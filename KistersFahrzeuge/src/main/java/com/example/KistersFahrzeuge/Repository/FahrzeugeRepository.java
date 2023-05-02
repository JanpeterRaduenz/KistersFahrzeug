package com.example.KistersFahrzeuge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.KistersFahrzeuge.Model.Fahrzeug;

@Repository
public interface FahrzeugeRepository extends JpaRepository<Fahrzeug, Integer>  {

}
