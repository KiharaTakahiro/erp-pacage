package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.RecivedOrderEntity;

/*
 * 受注用のSpecification
 * @author ngt
 */
public class RecivedOederSpec {
	
	private RecivedOederSpec() {}
	
	
	// 
	public static Specification<RecivedOrderEntity> recivedOrderSeqEquals(Long recivedOrderSeq){
		return recivedOrderSeq == null ? null : (root, query, builder) -> builder.equal(root.get("recivedOrderSeq"), recivedOrderSeq);
	}
	
	//
	public static Specification<RecivedOrderEntity> clientsSeqEquals(Long clientsSeq){
		return clientsSeq == null ? null : (root, query, builder) -> builder.equal(root.get("clientsSeq"), clientsSeq);
	}
	
	//
	public static Specification<RecivedOrderEntity> companySeqEquals(Long companySeq){
		return companySeq == null ? null : (root, query, builder) -> builder.equal(root.get("companySeq"), companySeq);
	}
	
	//
	public static Specification<RecivedOrderEntity> departmentSeqEquals(Long department){
		return department == null ? null : (root, query, builder) -> builder.equal(root.get("department"), department);
	}
	
	//
	public static Specification<RecivedOrderEntity> fromDate(String fromDate){
		return fromDate == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("recivedOrderDate"), fromDate);
	}
	
	//
	public static Specification<RecivedOrderEntity> toDate(String fromDate){
		return fromDate == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("recivedOrderDate"), fromDate);
	}
	
	//
	public static Specification<RecivedOrderEntity> fromTax(Integer fromTax){
		return fromTax == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("tax"), fromTax);
	}
	
	//
	public static Specification<RecivedOrderEntity> toTax(Integer toTax){
		return toTax == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("tax"), toTax);
	}
	
	//
	public static Specification<RecivedOrderEntity> fromTotal(Integer fromTotal){
		return fromTotal == null ? null : (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("total"), fromTotal);
	}
	
	//
	public static Specification<RecivedOrderEntity> toTotal(Integer toTotal){
		return toTotal == null ? null : (root, query, builder) -> builder.lessThanOrEqualTo(root.get("total"), toTotal);
	}
}
