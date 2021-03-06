package co.com.ceiba.estacionamiento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.ceiba.estacionamiento.entities.Parqueo;

public interface ParqueoRepository extends JpaRepository<Parqueo, Long> {

	@Query("select COUNT (p.tipoVehiculo)  from Parqueo p where p.tipoVehiculo = ?1 and p.fechaSalida IS NULL ")
	public long countByTipoVehiculo(String tipo);

	@Query("select p from Parqueo p where p.placa = ?1 and p.fechaSalida IS NULL ")
	public Parqueo findByPlaca(String placa);

	@Query("select p from Parqueo p where p.fechaSalida IS NULL ")
	public List<Parqueo> findAllParqueados();

}
