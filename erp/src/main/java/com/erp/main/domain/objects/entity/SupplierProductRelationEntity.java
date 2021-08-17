package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.SupplierProductRelationEntityFields;


/**
 * 仕入れ先商品連関テーブルへのロジック
 * 
 * @author 永戸
 *
 */
@Entity
@Table(name="SupplierProductRelation")
public class SupplierProductRelationEntity extends SupplierProductRelationEntityFields {

}
