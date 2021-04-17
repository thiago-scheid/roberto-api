package com.roberto.util.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaDTO {

	private String server;
	private Integer offset;
	private Integer limit;
	private Integer recordCount;
}
