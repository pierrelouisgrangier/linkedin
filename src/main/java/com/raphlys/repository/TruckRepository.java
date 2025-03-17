package com.raphlys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.raphlys.model.TruckModel;

public interface TruckRepository extends JpaRepository<TruckModel, Long> {

	List<TruckModel> findByBrand(String brand);

	List<TruckModel> findByBrandOrNameContains(String brand, String name);

	@Query("SELECT t FROM truck t where name like concat(:begin, '%')")
	List<TruckModel> maisOuQuiSontLesCamionsQuiCommencePar(@Param("begin") String begin);

	@Query("Select t from truck t where t.wheelSteering.brand = :brand")
	List<TruckModel> findByWheelBrand(@Param("brand") String brand);

	@Query(value = "select * from truck t inner join wheel_model w on t.wheel_id = w.id where w.brand = :brand", nativeQuery = true)
	List<TruckModel> findByWheelBrandNative(@Param("brand") String brand);
}
