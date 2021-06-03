package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.Id;

import com.sun.istack.NotNull;

public class SystemEntityFields extends BaseEntityFields {
	
	/*
	 * key
	 */
	@Id
	@Column(name="KEY")
	private String key;
	
	/*
	 * Value
	 */
	@NotNull
	@Column(name="VALUE")
	private String value;
		
	
}
