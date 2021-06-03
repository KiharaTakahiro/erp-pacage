package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="System")
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
