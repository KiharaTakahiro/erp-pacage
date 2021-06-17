package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.ClientsEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateClientsVo;


/**
 * 取引先テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Clients")
public class ClientsEntity extends ClientsEntityFileds {
	
	public static ClientsEntity create(CreateClientsVo vo) {

		ClientsEntity entity = new ClientsEntity();
		
		entity.setName(vo.getClientsName());
		return entity;
	}

	
}
