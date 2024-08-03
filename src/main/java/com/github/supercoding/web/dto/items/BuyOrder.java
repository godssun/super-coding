package com.github.supercoding.web.dto.items;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BuyOrder {
	private Integer itemId;
	private Integer itemNums;


}