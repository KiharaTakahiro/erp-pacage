package com.erp.main.domain.objects.valueobjects;
import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import com.erp.main.domain.objects.entity.UsersEntity;

/**
 * 認証に関わるValueObject
 * @author takah
 *
 */
public class AuthUserVo extends User  {
	
	private UsersEntity entity;
	
	public AuthUserVo(UsersEntity entity) {
		super(entity.getUserId(), entity.getPassword(),Collections.emptySet());
		this.entity = entity;
	}
	
	/**
	 * 姓
	 * @return
	 */
	public String getFirstName() {
		return this.entity.getFirstName();
	}
	
	/**
	 * 名
	 * @return
	 */
	public String getLastName() {
		return this.entity.getLastName();
	}
}
