package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.RecivedOrderDetailEntity;

/*
 * 受注詳細のSpecification
 * @author ngt
 */
public class RecivedOrderDetailSpec {
	
	private RecivedOrderDetailSpec() {}
	// 受注Seqによる検索
	public static Specification<RecivedOrderDetailEntity> recivedOrderSeqEquals(Long recivedOrderSeq){
		return recivedOrderSeq == null ? null : (root, query, builder) -> builder.equal(root.get("recivedOrderSeq"), recivedOrderSeq);
	}
}
