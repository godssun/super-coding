package com.github.supercoding.respository.electoricstore.storeSales;

public interface StoreSalesRepository {
	StoreSalesEntity findStoreSalesById(Integer storeId);

	void updateSalesAmount(Integer storeId, Integer stock);
}
