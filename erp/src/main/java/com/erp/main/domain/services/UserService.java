package com.erp.main.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.objects.entity.UsersEntity;
import com.erp.main.domain.objects.valueobjects.AuthUserVo;
import com.erp.main.domain.objects.valueobjects.CreateUserVo;
import com.erp.main.domain.repository.UsersRepository;

/**
 * ユーザ関連のサービス
 * 
 * @author 木原
 *
 */
@Service
public class UserService implements UserDetailsService{
	
	/**
	 * ユーザリポジトリ
	 */
	@Autowired
	private UsersRepository usersRepository;
	
	/**
	 * パスワードのエンコーダー
	 */
	@Autowired
	private PasswordEncoder passwordEncorder;
	
	/**
	 * ユーザ作成用リクエストのコントローラ
	 * @param request ユーザ作成用のリクエスト
	 */
	@Transactional
	public void registerUser(CreateUserVo vo) {
		
		// ユーザIDは重複させない
		UsersEntity aleadyUser = usersRepository.findByUserId(vo.getUserId());
		if(aleadyUser != null) {
			throw new AppException("登録済みのユーザIDのため登録できません。ユーザIDを変更してください。");
		}
		
		// ユーザ作成用のエンティティ生成
		UsersEntity registerUser = UsersEntity.create(vo.getUserId(), passwordEncorder.encode(vo.getPassword()), vo.getFirstName(), vo.getLastName(),vo.getEmail());
		usersRepository.save(registerUser);
	}
	
	/**
	 * ユーザIDをもとに認証Voを返却
	 */
	@Override
	@Transactional
	public AuthUserVo loadUserByUsername(String userId) throws UsernameNotFoundException {
		UsersEntity loginUser = usersRepository.findByUserId(userId);
		
		// ユーザがいないときはエラーメッセージを表示して終了する
		if(loginUser == null) {
			throw new AppException(String.format("該当のユーザを取得できませんでした。 userId: %s", userId));
		}
		return new AuthUserVo(loginUser);
	}
	
}
