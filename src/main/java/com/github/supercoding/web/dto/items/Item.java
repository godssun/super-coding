package com.github.supercoding.web.dto.items;

import com.github.supercoding.respository.electoricstore.items.ItemEntity;
import lombok.*;


@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Item {

	private String id;
	private String name;
	private String type;
	private Integer price;
	private Spec spec;


	public Item(String id, ItemBody itemBody){
		this.id = id;
		this.name = itemBody.getName();
		this.type = itemBody.getType();
		this.price = itemBody.getPrice();
		this.spec = itemBody.getSpec();
	}

}
