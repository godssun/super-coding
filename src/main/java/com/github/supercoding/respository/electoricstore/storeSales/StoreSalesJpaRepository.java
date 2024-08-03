package com.github.supercoding.respository.electoricstore.storeSales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreSalesJpaRepository extends JpaRepository<StoreSalesEntity, Integer> {

	@Query("SELECT s FROM StoreSalesEntity s JOIN FETCH s.itemEntities")
	List<StoreSalesEntity> findAllFetchJoin();
}
