package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.RecivedOrderDetailEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo.CreateRecivedOrderDetailVo;

import lombok.Getter;


/**
 * 受注詳細
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="RecivedOrderDetail")
public class RecivedOrderDetailEntity extends RecivedOrderDetailEntityFileds {
	
	/**
	 * 登録用のエンティティ作成処理
	 * @param vo
	 * @return
	 */
	
	public static RecivedOrderDetailEntity create(CreateRecivedOrderDetailVo vo) {

		var entity = new RecivedOrderDetailEntity();
		
		// 商品SEQ
		entity.setDeriveryDate(vo.getDeriveryDate());
		// 値引
		entity.setDiscount(vo.getDiscount());
		// ロットSEQ
		entity.setLotSeq(vo.getLotSeq());
		// 商品SEQ
		entity.setProductSeq(vo.getProductSeq());
		// 数量
		entity.setQuantity(vo.getQuantity());
		// 状態
		entity.setStatus(vo.getStatus());	
		return entity;
		
	}

	/**
	 * 受注テーブルの取得
	 */
	@Getter
	@ManyToOne
	@JoinColumn(name = "RECIVED_ORDER_SEQ", insertable = false, updatable = false)
	private RecivedOrderEntity recivedOrder;	
	
	/**
	 * 商品テーブルの取得
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="PRODUCT_SEQ", insertable = false, updatable = false)
	private ProductEntity prodcut;
	
	/**
	 * ロットテーブルの取得
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="LOT_SEQ", insertable = false, updatable = false)
	private LotEntity lot;
}
