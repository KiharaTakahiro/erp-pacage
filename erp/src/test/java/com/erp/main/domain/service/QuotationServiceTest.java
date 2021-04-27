package com.erp.main.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.erp.main.domain.services.QuotationService;

/**
 * UserServiceのパターン網羅用のテスト
 * @author takah
 *
 */
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class QuotationServiceTest {
	
	@InjectMocks
	private QuotationService quotationService = new QuotationService();
	
	
}
