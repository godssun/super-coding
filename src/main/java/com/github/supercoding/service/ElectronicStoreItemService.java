package com.github.supercoding.service;

import com.github.supercoding.respository.electoricstore.items.ElectronicStoreItemJpaRepository;
import com.github.supercoding.respository.electoricstore.items.ElectronicStoreItemRepository;
import com.github.supercoding.respository.electoricstore.items.ItemEntity;
import com.github.supercoding.respository.electoricstore.storeSales.StoreSalesEntity;
import com.github.supercoding.respository.electoricstore.storeSales.StoreSalesJpaRepository;
import com.github.supercoding.respository.electoricstore.storeSales.StoreSalesRepository;
import com.github.supercoding.service.exceptions.NotAcceptException;
import com.github.supercoding.service.exceptions.NotFoundException;
import com.github.supercoding.service.mapper.ItemMapper;
import com.github.supercoding.web.dto.items.BuyOrder;
import com.github.supercoding.web.dto.items.Item;
import com.github.supercoding.web.dto.items.ItemBody;
import com.github.supercoding.web.dto.items.StoreInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElectronicStoreItemService {


	private final ElectronicStoreItemJpaRepository electronicStoreItemJpaRepository;
	private final StoreSalesJpaRepository  storeSalesJpaRepository;


	public List<Item> findAllItem() {
		List<ItemEntity> itemEntities = electronicStoreItemJpaRepository.findAll();
		return itemEntities.stream().map(ItemMapper.INSTANCE::itemEntityToItem).collect(Collectors.toList());
	}

	public Integer saveItem(ItemBody itemBody) {
		ItemEntity itemEntity = ItemMapper.INSTANCE.idAndItemBodyToItemEntity(null, itemBody);
		ItemEntity itemEntityCreated;
		try {
			itemEntityCreated = electronicStoreItemJpaRepository.save(itemEntity);
		} catch (RuntimeException exception){
			throw new NotAcceptException("Item을 저장하는 도중에 Error 가 발생하였습니다.");
		}

		return itemEntityCreated.getId();
	}

	public Item findItemById(String id) {
		Integer itemdId = Integer.parseInt(id);
		ItemEntity itemEntityFindById = electronicStoreItemJpaRepository.findById(itemdId).orElseThrow(() -> new NotFoundException("Item not found"));
		Item itemFound = ItemMapper.INSTANCE.itemEntityToItem(itemEntityFindById);
		return itemFound;

	}

	public List<Item> findItemByIds(List<String> ids) {
		List<Integer> itemdIds = ids.stream().map(Integer::parseInt).collect(Collectors.toList());
		List<ItemEntity> itemEntityFindByIds = electronicStoreItemJpaRepository.findAll();
		List<Item> itemFound = itemEntityFindByIds.stream().map(ItemMapper.INSTANCE::itemEntityToItem).collect(Collectors.toList());
		return itemFound;
	}

	public void deleteItem(String id) {
		Integer itemdId = Integer.parseInt(id);
		electronicStoreItemJpaRepository.deleteById(itemdId);
	}

	@Transactional(transactionManager = "tmJpa1")
	public Item updateItemById(String id, ItemBody itemBody) {
		Integer itemdId = Integer.parseInt(id);
		ItemEntity itemEntityUpdated = electronicStoreItemJpaRepository.findById(itemdId).orElseThrow(() -> new NotFoundException("Item not found"));
		itemEntityUpdated.setItemBody(itemBody);
		Item itemUpdated = ItemMapper.INSTANCE.itemEntityToItem(itemEntityUpdated);
		return itemUpdated;
	}

	@Transactional(transactionManager = "tmJpa1")
	public Integer buyItems(BuyOrder buyOrder) {
		// 1. BuyOrder 에서 상품 ID와 수량을 얻어낸다.
		// 2. 상품을 조회하여 수량이 얼마나 있는 지 확인한다.
		// 3. 상품의 수량과 가격을 가지고 계산하여 총 가격을 구한다.
		// 4. 상품의 재고에 기존 계산한 재고를 구매하는 수량을 뺸다.
		// 5. 상품 구매하는 수량 * 가격 만큼 가계 매상으로 올린다.
		// (단, 재고가 아예 없거나 매장을 찾을 수 없으면 살 수 없다. )

		Integer itemId = buyOrder.getItemId();
		Integer itemNums = buyOrder.getItemNums();

		ItemEntity itemEntity = electronicStoreItemJpaRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
		if (itemEntity.getStoreSales().isEmpty() ) throw new RuntimeException("매장을 찾을 수 없습니다.");
		if (itemEntity.getStock() <= 0) throw new RuntimeException("상품의 재고가 없습니다.");

		Integer successBuyItemNums;
		if ( itemNums >= itemEntity.getStock() ) successBuyItemNums = itemEntity.getStock();
		else successBuyItemNums = itemNums;

		Integer totalPrice = successBuyItemNums * itemEntity.getPrice();

		// Item 재고 감소
    itemEntity.setStock(itemEntity.getStock() - successBuyItemNums);

		// 매장 매상 추가
		StoreSalesEntity storeSales = itemEntity.getStoreSales().orElseThrow(() -> new NotFoundException("요청하신 Store 해당하는 StoreSale 없습니다."));

		storeSales.setAmount(storeSales.getAmount() + totalPrice);

		return successBuyItemNums;
	}

	public List<Item> findItemsByTypes(List<String> types) {
		List<ItemEntity> itemEntities = electronicStoreItemJpaRepository.findItemEntitiesByTypeIn(types);
		 return itemEntities.stream().map(ItemMapper.INSTANCE::itemEntityToItem).collect(Collectors.toList());
	}

	public List<Item> findItemsOrderByPrices(Integer maxValue) {
		List<ItemEntity> itemEntities = electronicStoreItemJpaRepository.findItemEntitiesByPriceLessThanEqualOrderByPriceAsc(maxValue);
		return itemEntities.stream().map(ItemMapper.INSTANCE::itemEntityToItem).collect(Collectors.toList());
	}

	public Page<Item> findAllWithPageable(Pageable pageable) {
		Page<ItemEntity> itemEntities = electronicStoreItemJpaRepository.findAll(pageable);
		return itemEntities.map(ItemMapper.INSTANCE::itemEntityToItem);
	}



	public Page<Item> findAllWithPageable(List<String> types, Pageable pageable) {
		Page<ItemEntity> itemEntities = electronicStoreItemJpaRepository.findAllByTypeIn(types, pageable);
		return itemEntities.map(ItemMapper.INSTANCE::itemEntityToItem);
	}

	@Transactional(transactionManager = "tmJpa1")
	public List<StoreInfo> findAllStoreInfo() {
		List<StoreSalesEntity> storeSales = storeSalesJpaRepository.findAllFetchJoin();
		log.info("======================== N + 1 확인용 로그 =============================");
		List<StoreInfo> storeInfos = storeSales.stream().map(StoreInfo::new).collect(Collectors.toList());
		return storeInfos;
	}
}
