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
	 * 仕入先SEQでの検索
	 * @param supplierProductsSeq
	 * @return
	 */
	public static Specification<SupplierProductEntity> supplierProductsSeqEquals(Long supplierProductsSeq){
		return supplierProductsSeq == null ? null : (root, query, builder) -> builder.equal(root.get("supplierProductsSeq"), supplierProductsSeq);
	}
	
	/*
	 * 仕入先名での検索
	 * @param supplierProductsName
	 * @return
	 */
	public static Specification<SupplierProductEntity> supplierProductsNameEquals(String supplierProductsName){
		return supplierProductsName == null ? null : (root, query, builder) ->builder.like(root.get("name"),"%" + supplierProductsName + "%");
	}

}