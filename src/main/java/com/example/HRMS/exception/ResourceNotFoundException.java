package com.example.HRMS.exception;

public class ResourceNotFoundException extends RuntimeException {
private String resourceName;
private String fieldName;
private Long fieldId;
public ResourceNotFoundException(String resourceName, String fieldName, Long fieldId) {
	super(String.format("%s not found with %s:%s",resourceName,fieldName,fieldId));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldId = fieldId;
}
public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}
public String getFieldName() {
	return fieldName;
}
public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
}
public Long getFieldId() {
	return fieldId;
}
public void setFieldId(Long fieldId) {
	this.fieldId = fieldId;
}

}
