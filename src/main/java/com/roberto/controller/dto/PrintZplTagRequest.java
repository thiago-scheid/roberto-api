package com.roberto.controller.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class PrintZplTagRequest implements Serializable {

	private static final long serialVersionUID = -491027265505819708L;

	private String printerName;
	private String zpl;
}