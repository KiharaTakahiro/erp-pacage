package com.erp.main.domain.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.objects.entity.UsersEntity;
import com.erp.main.domain.objects.valueObjects.AuthUserVo;
import com.erp.main.domain.objects.valueObjects.CreateUserVo;
import com.erp.main.domain.repository.UsersRepository;
import com.erp.main.domain.services.UserService;

/**
 * UserServiceのパターン網羅用のテスト
 * @author takah
 *
 */
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService = new UserService();
	
	@Mock
	private UsersRepository usersRepository; 
	
	@Mock 
	private PasswordEncoder passwordEncorder;

	
	/**
	 * 正常系1
	 * 
	 * テスト結果: 正常に登録できること
	 * テスト観点: 既に登録済みのユーザが存在していない場合
	 */
	@Test
	public void registerUserSuccessCase1() {
		// テストデータ作成
		CreateUserVo testData = new CreateUserVo();
		testData.setUserId("userId");
		testData.setPassword("password");
		testData.setFirstName("firstName");
		testData.setLastName("lastName");
		testData.setEmail("email");
		// 登録済みのユーザがいないように返却する
		Mockito.when(this.usersRepository.findByUserId("userId")).thenReturn(null);
		// パスワードの暗号化はロジックに直接的に関わらないのでそのままを返却
		Mockito.when(this.passwordEncorder.encode("password")).thenReturn("password");
		
		// 確認対象を実行
		userService.registerUser(testData);
		
		// 検証用のデータ作成
		UsersEntity verifyUser = UsersEntity.create("userId", "password", "firstName", "lastName","email");
		Mockito.verify(usersRepository, times(1)).save(verifyUser);
	}

	/**
	 * 異常系1
	 * 
	 * テスト結果: 登録ができずエラーとなること
	 * テスト観点: 既に登録済みのユーザがいる場合
	 */
	@Test
	public void registerUserErroCase1() {
		// テストデータ作成
		CreateUserVo testData = new CreateUserVo();
		testData.setUserId("userId");
		testData.setPassword("password");
		testData.setFirstName("firstName");
		testData.setLastName("lastName");
		testData.setEmail("email");

		UsersEntity aleadyUser = UsersEntity.create("userId", "password", "firstName", "lastName","email");		
		// 登録済みのユーザがいないように返却する
		Mockito.when(this.usersRepository.findByUserId("userId")).thenReturn(aleadyUser);
		
		// 確認対象を実行
		Assertions.assertThrows(AppException.class, () -> userService.registerUser(testData));
		
	}	
	
	/**
	 * 正常系1
	 * 
	 * テスト結果: ユーザの認証Voを返却すること
	 */
	@Test
	public void loadUserByUsernameSuccessCase1() {
		UsersEntity loginUser = UsersEntity.create("userId", "password", "firstName", "lastName","email");
		Mockito.when(this.usersRepository.findByUserId("userId")).thenReturn(loginUser);		
		AuthUserVo authUser = userService.loadUserByUsername("userId");
		Assertions.assertEquals(authUser, new AuthUserVo(loginUser));
		
	}
}
