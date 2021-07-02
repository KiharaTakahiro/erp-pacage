package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.ClientsEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateClientsVo;
import com.erp.main.domain.objects.valueobjects.UpdateClientVo;


/**
 * 取引先テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Clients")
public class ClientsEntity extends ClientsEntityFileds {
	
	/**
	 * 取引先エンティティ生成
	 * @param vo
	 * @return
	 */
	public static ClientsEntity create(CreateClientsVo vo) {

		var entity = new ClientsEntity();
		
		// 取引先名
		entity.setName(vo.getClientsName());
		
		return entity;
	}

	/*
	 * 取引先編集処理のEntity
	 * @param vo
	 */
	
	public void update(UpdateClientVo vo) {
		// 取引先名入れ替え
		this.setName(vo.getClient().getClientsName());
	}
}
