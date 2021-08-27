package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.DepartmentEntity;

/*
 * 部署用のSpecification
 * @author ngt
 */
public class DepartmentSpec {
	private DepartmentSpec() {}
	/*
	 * 部署SEQでの検索
	 * @param departmentSeq
	 * @return
	 */
	public static Specification<DepartmentEntity> departmentSeqEquals(Long departmentSeq){
		return departmentSeq == null ? null : (root, query, builder) -> builder.equal(root.get("departmentSeq"), departmentSeq);
	}
	
	/*
	 * 会社SEQでの検索
	 * @param companySeq
	 * @return
	 */
	public static Specification<DepartmentEntity> companySeqEquals(Long companySeq){
		return companySeq == null ? null : (root, query, builder) -> builder.equal(root.get("companySeq"), companySeq);
	}
	
	/*
	 * 部署名での検索
	 * @param departmentName
	 * @return
	 */
	public static Specification<DepartmentEntity> departmentNameEquals(String departmentName){
		return departmentName == null ? null : (root, query, builder) ->builder.like(root.get("name"),"%" + departmentName + "%");
	}

}
