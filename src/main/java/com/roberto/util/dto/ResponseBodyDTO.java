package com.roberto.util.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.roberto.util.error.ErrorDTO;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseBodyDTO<T> implements Serializable {

	private static final long serialVersionUID = 1388706364317434472L;

	protected MetaDTO meta;
	protected List<ErrorDTO> errors;
	protected List<T> records;

	public ResponseBodyDTO() {
	}

	public ResponseBodyDTO(List<ErrorDTO> errors) {
		this.meta = null;
		this.errors = errors;
	}

	public ResponseBodyDTO(ErrorDTO error) {
		this.meta = null;
		this.errors = new ArrayList<>();
		this.errors.add(error);
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDTO> errors) {
		this.errors = errors;
	}

	public void addError(ErrorDTO error) {
		if (errors == null) {
			errors = new ArrayList<>();
		}

		errors.add(error);
	}

	public void addErrors(List<ErrorDTO> errors) {
		if ((this.errors == null) || this.errors.isEmpty()) {
			this.errors = errors;
		} else {
			errors.forEach(error -> {
				this.errors.add(error);
			});
		}
	}

	public void setMeta(MetaDTO meta) {
		this.meta = meta;
	}

	public MetaDTO getMeta() {

		if (meta == null) {
			String hostName = "";
			try {
				hostName = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				hostName = "UnknownHost";
			}

			return MetaDTO.builder().server(hostName).limit(this.records == null ? 0 : this.records.size()).offset(0)
					.recordCount(this.records == null ? 0 : this.records.size()).build();
		} else {
			return meta;
		}
	}

	public boolean isSucess() {
		if ((errors == null) || errors.isEmpty()) {
			return true;
		}

		return false;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public void addRecord(T record) {
		if (this.records == null) {
			records = new ArrayList<>();
		}

		records.add(record);
	}
}