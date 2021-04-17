package com.roberto.controller.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemPrinter implements Serializable {

	private static final long serialVersionUID = 6345316260154511869L;

	private Long id;
	private String name;
}
