package com.example.HRMS.exception;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException {
private String resourceName;
private String fieldName;
private Long fieldId;
private String resourceName2;
private String fieldName2;
private List<Long> projectIds;
public ResourceNotFoundException(String resourceName, String fieldName, Long fieldId) {
	super(String.format("%s not found with %s:%s",resourceName,fieldName,fieldId));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldId = fieldId;
}
public ResourceNotFoundException(String resourceName2, String fieldName2, List<Long> projectIds) {
	super(String.format("%s not found with %s:%s",resourceName2,fieldName2,projectIds));
	this.setResourceName2(resourceName2);
	this.setFieldName2(fieldName2);
	this.setProjectIds(projectIds);
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
public String getResourceName2() {
	return resourceName2;
}
public void setResourceName2(String resourceName2) {
	this.resourceName2 = resourceName2;
}
public String getFieldName2() {
	return fieldName2;
}
public void setFieldName2(String fieldName2) {
	this.fieldName2 = fieldName2;
}
public List<Long> getProjectIds() {
	return projectIds;
}
public void setProjectIds(List<Long> projectIds) {
	this.projectIds = projectIds;
}

}
