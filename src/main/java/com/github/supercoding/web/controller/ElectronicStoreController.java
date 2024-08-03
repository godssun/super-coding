package com.github.supercoding.web.controller;

import com.github.supercoding.service.ElectronicStoreItemService;
import com.github.supercoding.web.dto.items.BuyOrder;
import com.github.supercoding.web.dto.items.Item;
import com.github.supercoding.web.dto.items.ItemBody;
import com.github.supercoding.web.dto.items.StoreInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ElectronicStoreController {


	private final ElectronicStoreItemService electronicStoreItemService;

	@Cacheable(value = "items", key = "#root.methodName")
	@Operation(summary = "모든 Items을 검색")
	@GetMapping("/items")
	public List<Item> getAllItems() {

		List<Item> items = electronicStoreItemService.findAllItem();

		return items;
	}

	@PostMapping("/items")
	public String registerItem(@RequestBody ItemBody itemBody) {
		Integer itemdId = electronicStoreItemService.saveItem(itemBody);
		return "ID: " + itemdId;
	}

	@Cacheable(value = "items", key = "#id")
	@GetMapping("/items/{id}")
	public Item getItemById(@PathVariable String id) {
		return electronicStoreItemService.findItemById(id);
	}

	@GetMapping("/items-query")
	public Item getItemByQueryId(@RequestParam("id") String id) {
		return electronicStoreItemService.findItemById(id);
	}

	@GetMapping("/items-queries")
	public List<Item> getItemByQueryId(@RequestParam("id") List<String> ids) {

		List<Item> items = electronicStoreItemService.findItemByIds(ids);

		return items;
	}

	@CacheEvict(value = "items", allEntries = true)
	@DeleteMapping("/items/{id}")
	public String deleteItemById(@PathVariable String id) {
		electronicStoreItemService.deleteItem(id);
		return "Object with id =" + id + "has been deleted";
	}

	@CacheEvict(value = "items", allEntries = true)
	@PutMapping("/items/{id}")
	public Item updateItem(@PathVariable String id, @RequestBody ItemBody itemBody) {
		return electronicStoreItemService.updateItemById(id,itemBody);
	}

	@CacheEvict(value = "items", allEntries = true)
	@PostMapping("/items/buy")
	public String buyItem(@RequestBody BuyOrder buyOrder){
		Integer orderItemNums = electronicStoreItemService.buyItems(buyOrder);
		return "요청하신 Item 중 " + orderItemNums + "개를 구매 하였습니다.";
	}

	@GetMapping("/items-types")
	public List<Item> findItemByTypes(
			@RequestParam("type") List<String> types){

		List<Item> items = electronicStoreItemService.findItemsByTypes(types);

		return items;
	}


	@GetMapping("/items-prices")
	public List<Item> findItemByPrices(
			@RequestParam("max") Integer maxValue){
		return electronicStoreItemService.findItemsOrderByPrices(maxValue);
	}


	@GetMapping("/items-page")
	public Page<Item> findItemsPagination(Pageable pageable){
		return electronicStoreItemService.findAllWithPageable(pageable);
	}

	@GetMapping("/items-types-page")
	public Page<Item> findItemsPagination(@RequestParam("type") List<String> types, Pageable pageable){
		return electronicStoreItemService.findAllWithPageable(types, pageable);
	}

	@GetMapping("/stores")
	public List<StoreInfo> findAllStoreInfo(){
		return electronicStoreItemService.findAllStoreInfo();
	}
}