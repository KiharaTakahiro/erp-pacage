package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.SupplierEntity;

/*
 * 仕入先用のSpecification
 * @author takayama
 */
public class SupplierSpec {
	private SupplierSpec() {}
	
	/*
	 * 仕入先SEQでの検索
	 * @param supplierSeq
	 * @return
	 */
	public static Specification<SupplierEntity> supplierSeqEquals(Long supplierSeq){
		return supplierSeq == null ? null : (root, query, builder) -> builder.equal(root.get("supplierSeq"), supplierSeq);
	}
	
	/*
	 * 仕入先名での検索
	 * @param supplierName
	 * @return
	 */
	public static Specification<SupplierEntity> supplierNameEquals(String supplierName){
		return supplierName == null ? null : (root, query, builder) ->builder.like(root.get("name"),"%" + supplierName + "%");
	}

}