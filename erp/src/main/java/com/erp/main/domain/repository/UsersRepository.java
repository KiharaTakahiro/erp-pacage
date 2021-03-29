package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.UsersEntity;

/**
 * ユーザリポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
	
	/**
	 * ユーザIDをもとにUserEntityを取得
	 * @return
	 */
	public UsersEntity findByUserId(String userId);
}
