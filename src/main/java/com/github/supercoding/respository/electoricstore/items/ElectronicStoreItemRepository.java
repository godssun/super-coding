package com.github.supercoding.respository.electoricstore.items;



import java.util.List;



public interface ElectronicStoreItemRepository {


	List<ItemEntity> findAllItems();

	Integer saveItem(ItemEntity itemEntity);

	ItemEntity findItemById(Integer id);

	List<ItemEntity> findItemByIds(List<Integer> ids);

	void deleteItem(Integer id);

	ItemEntity updateItemById(Integer id, ItemEntity itemEntity);

	void updateItemStock(Integer itemId, Integer stock);
}
