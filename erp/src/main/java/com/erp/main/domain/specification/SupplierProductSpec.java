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
	public static Specification<SupplierProductEntity> supplierProductSeqEquals(Long supplierProductSeq){
		return supplierProductSeq == null ? null : (root, query, builder) -> builder.equal(root.get("supplierProductSeq"), supplierProductSeq);
	}
	
	/*
	 * 仕入先名での検索
	 * @param supplierProductsName
	 * @return
	 */
	public static Specification<SupplierProductEntity> supplierProductNameEquals(String supplierProductName){
		return supplierProductName == null ? null : (root, query, builder) ->builder.like(root.get("name"),"%" + supplierProductName + "%");
	}

}