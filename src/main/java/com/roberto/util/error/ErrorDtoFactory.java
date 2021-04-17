package com.roberto.util.error;

public class ErrorDtoFactory {

	public static ErrorDTO getMissingParameter(String info, String field) {
		return new ErrorDTO("Missing " + info + " parameter " + field,
				"Field " + field + " is required and can not be empty", ErrorCodeEnum.MISSING_PARAMETERS.getCode());
	}

	public static ErrorDTO getUnknownParameter(String info, String field) {
		return new ErrorDTO("Unknown " + info + " parameter " + field, "Field " + field + " is unknown",
				ErrorCodeEnum.UNKNOWN_PARAMETERS.getCode());
	}

	public static ErrorDTO getMissingRequestBody() {
		return new ErrorDTO("Missing request body", "Missing request body",
				ErrorCodeEnum.MISSING_REQUEST_BODY.getCode());
	}

	public static ErrorDTO getNotFound(String name) {
		return new ErrorDTO(name + " not found ", " You attempted to get a " + name + ", but did not find any",
				ErrorCodeEnum.NOT_FOUND.getCode());
	}

	public static ErrorDTO getAlreadyExists(String name) {
		return new ErrorDTO(name + " already exists ", " You attempted to create a " + name + ", but already exists",
				ErrorCodeEnum.ALREADY_EXISTS.getCode());
	}

	public static ErrorDTO getInternalServerError(String message) {
		return new ErrorDTO(message, "Sorry, something went wrong", ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode());
	}
}