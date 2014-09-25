package com.johnkuper.mapper;

import java.lang.reflect.Field;

public class FieldsContainer {

	public Field srcField;
	public Field destiField;

	public FieldsContainer(Field srcField, Field destiField) {
		this.srcField = srcField;
		this.destiField = destiField;
	}

	public Field getSrcField() {
		return srcField;
	}

	public void setSrcField(Field srcField) {
		this.srcField = srcField;
	}

	public Field getDestiField() {
		return destiField;
	}

	public void setDestiField(Field destiField) {
		this.destiField = destiField;
	}

}
