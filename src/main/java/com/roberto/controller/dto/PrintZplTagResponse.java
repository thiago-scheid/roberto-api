package com.roberto.controller.dto;

import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrintZplTagResponse implements Serializable {

	private static final long serialVersionUID = 2193015566326495334L;

	private String message;
	private String system;	
	private boolean status;
}
