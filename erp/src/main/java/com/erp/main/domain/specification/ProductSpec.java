package com.erp.main.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.erp.main.domain.objects.entity.ProductEntity;

/*
 * 商品用のSpecification
 * @author takayama
 */
public class ProductSpec {
private ProductSpec() {}
	
	/*
	 * 商品SEQでの検索
	 * @param productSeq
	 * @return
	 */
	public static Specification<ProductEntity> productSeqEquals(Long productSeq){
		return productSeq == null ? null : (root, query, builder) -> builder.equal(root.get("productSeq"), productSeq);
	}
	
	/*
	 * 商品名での検索
	 * @param productName
	 * @return
	 */
	public static Specification<ProductEntity> productNameEquals(String productName){
		return productName == null ? null : (root, query, builder) ->builder.like(root.get("name"),"%" + productName + "%");
	}
}
