package co.com.ceiba.estacionamiento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.estacionamiento.entities.Parqueo;

public interface ParqueoRepository extends JpaRepository<Parqueo, Long> {

}
