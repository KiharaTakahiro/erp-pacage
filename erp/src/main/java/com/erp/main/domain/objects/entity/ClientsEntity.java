package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.ClientsEntityFileds;


/**
 * 取引先テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Clients")
public class ClientsEntity extends ClientsEntityFileds {

	
}
