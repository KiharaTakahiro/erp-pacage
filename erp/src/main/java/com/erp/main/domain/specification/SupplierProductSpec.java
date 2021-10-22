package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.SupplierProductEntity;

/*
 * 取引先用のSpecification
 * @author 高山
 */
public class SupplierProductSpec {
	private SupplierProductSpec() {}
	/*
	 * 仕入商品SEQでの検索
	 * @param supplierProductsSeq
	 * @return
	 */
	public static Specification<SupplierProductEntity> supplierProductSeqEquals(Long supplierProductSeq){
		return supplierProductSeq == null ? null : (root, query, builder) -> builder.equal(root.get("supplierProductSeq"), supplierProductSeq);
	}
	
	/*
	 * 仕入商品名での検索
	 * @param supplierProductsName
	 * @return
	 */
	public static Specification<SupplierProductEntity> supplierProductNameEquals(String supplierProductName){
		return supplierProductName == null ? null : (root, query, builder) ->builder.like(root.get("name"),"%" + supplierProductName + "%");
	}
	
	/**
	 * 仕入れ価格Fromで検索
	 * @param purchaseUnitPrice
	 * @return
	 */
	public static Specification<SupplierProductEntity> purchaseUnitPriceFrom(Long purchaseUnitPriceFrom){
		return purchaseUnitPriceFrom == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("purchaseUnitPrice"), purchaseUnitPriceFrom);
	}
	
	/**
	 * 仕入れ価格Toで検索
	 * @param purchaseUnitPrice
	 * @return
	 */
	public static Specification<SupplierProductEntity> purchaseUnitPriceTo(Long purchaseUnitPriceTo){
		return purchaseUnitPriceTo == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("purchaseUnitPrice"), purchaseUnitPriceTo);
	}

}