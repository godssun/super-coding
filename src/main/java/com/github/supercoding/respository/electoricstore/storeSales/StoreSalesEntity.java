package com.github.supercoding.respository.electoricstore.storeSales;

import com.github.supercoding.respository.electoricstore.items.ItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
@Entity
@Table(name = "store_sales")
public class StoreSalesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "store_name", length = 30)
	private String storeName;

	@Column(name = "amount", nullable = false, columnDefinition = "DEFAULT 0 CHECK(amount) >= 0")
	private Integer amount;

	@OneToMany(mappedBy = "storeSalesEntity", fetch = FetchType.EAGER)
	private List<ItemEntity> itemEntities;
}
