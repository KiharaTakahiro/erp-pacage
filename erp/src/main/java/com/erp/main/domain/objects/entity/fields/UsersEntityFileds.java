package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザを管理する
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class UsersEntityFileds extends BaseEntityFields {

	/**
	 * ユーザSEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "user_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "USER_SEQ")
	private Long userSeq;

	/**
	 * ユーザID
	 */
	@NotNull
	@Column(name = "USER_ID")
	private String userId;
	
	/**
	 * 部署SEQ
	 */
	@Column(name = "DEPARTMENT_SEQ")
	private Long departmentSeq;
	
	/**
	 * パスワード
	 */
	@NotNull
	@Column(name = "PASSWORD")
	private String password;
	
	/**
	 * 姓
	 */
	@NotNull
	@Column(name = "FIRST_NAME")
	private String firstName;

	/**
	 * 名
	 */
	@NotNull
	@Column(name = "LAST_NAME")
	private String lastName;
	
	/**
	 * Email
	 */
	@Column(name = "EMAIL")
	private String email;
	
	/**
	 * ロックフラグ
	 */
	@NotNull
	@Column(name = "LOCK_FLAG")
	private Boolean lockFlag;

	/**
	 * 連続ログイン失敗回数
	 */
	@NotNull
	@Column(name="FAILUER_COUNT")
	private Integer failuerCount;
	
}
