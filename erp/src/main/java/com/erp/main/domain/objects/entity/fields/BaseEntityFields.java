package com.erp.main.domain.objects.entity.fields;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;

/**
 * エンティティの基底クラス
 * 
 * @author 木原
 *
 */
@Data
@MappedSuperclass
public class BaseEntityFields implements Serializable {

	private static final long serialVersionUID = 1L;
	
    /**
     * 登録日
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INSERT_DATE")
    private Date insertDate;

    /**
     * 更新日
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /**
     * バージョン
     */
    @Version
    @Column(name = "VERSION")
    private Long version;

    /**
     * 登録前処理
     */
    @PrePersist
    public void prePersist() {
        var date = new Date();
        insertDate = date;
        updateDate = date;
    }

}
