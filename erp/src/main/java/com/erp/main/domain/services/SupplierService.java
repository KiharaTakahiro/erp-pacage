package com.erp.main.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.OrderRepository;

/*
 * 受注のサービス
 * @author takayama
 */

public class SupplierService {
	/*
	 * 発注レポジトリ
	 */
	@Autowired
	private OrderRepository orderRepository; 

	/**
	 * 取引先マスタのリポジトリ
	 */
	@Autowired
	private ClientsRepository clientsRepository;
	

	/**
	 * 受注作成処理
	 * @param createRecivedOrderVo
	 */
	@Transactional
	public void  createOrder(CreateOrderVo createOrderVo) {
		
	}
	

}
