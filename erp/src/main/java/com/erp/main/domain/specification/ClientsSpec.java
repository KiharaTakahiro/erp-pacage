package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.ClientsEntity;

/*
 * 取引先用のSpecification
 * @author ngt
 */

public class ClientsSpec {
	private ClientsSpec() {}
	
	/*
	 * 取引先SEQでの検索
	 * @param clientsSeq
	 * @return
	 */
	public static Specification<ClientsEntity> clientsSeqEquals(Long clientsSeq){
		return clientsSeq == null ? null : (root, query, builder) -> builder.equal(root.get("clientsSeq"), clientsSeq);
	}

}
