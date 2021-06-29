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
	 * TODO:
	 * ここにマッピングして自信を変更するメソッド
	 * UPDATE
	 */
}
