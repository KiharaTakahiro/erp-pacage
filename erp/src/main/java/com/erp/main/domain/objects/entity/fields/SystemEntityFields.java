package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
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
