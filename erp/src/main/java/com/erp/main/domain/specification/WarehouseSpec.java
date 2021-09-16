package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.WarehouseEntity;

/*
 * 倉庫用のSpecification
 * @author takayama
 */
public class WarehouseSpec {
	private WarehouseSpec() {}
	
	/*
	 * 倉庫SEQでの検索
	 * @param warehouseSeq
	 * @return
	 */
	public static Specification<WarehouseEntity> warehouseSeqEquals(Long warehouseSeq){
		return warehouseSeq == null ? null : (root, query, builder) -> builder.equal(root.get("warehouseSeq"), warehouseSeq);
	}
	
	/*
	 * 倉庫名での検索
	 * @param warehouseName
	 * @return
	 */
	public static Specification<WarehouseEntity> warehouseNameEquals(String warehouseName){
		return warehouseName == null ? null : (root, query, builder) ->builder.like(root.get("name"),"%" + warehouseName + "%");
	}

}
