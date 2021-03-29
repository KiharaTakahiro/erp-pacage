package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.UsersEntityFileds;

/**
 * ユーザテーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Users")
public class UsersEntity extends UsersEntityFileds{
	
	/**
	 * ユーザエンティティの作成
	 * @param userId ユーザID
	 * @param password パスワード
	 * @param firstName 姓
	 * @param lastName 名
	 * @param email Emailアドレス
	 * @return
	 */
	public static UsersEntity create(String userId, String password,String firstName, String lastName, String email) {
		UsersEntity entity =  new UsersEntity();
		// ユーザID
		entity.setUserId(userId);
		// パスワード
		entity.setPassword(password);
		// 姓
		entity.setFirstName(firstName);
		// 名
		entity.setLastName(lastName);
		// emailアドレス
		entity.setEmail(email);
		// ロックフラグ
		entity.setLockFlag(Boolean.FALSE);
		// 連続ログイン失敗回数
		entity.setFailuerCount(0);
		return entity;
	}

}
