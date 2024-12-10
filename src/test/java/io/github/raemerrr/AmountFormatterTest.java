package io.github.raemerrr;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountFormatterTest {

	private static final AmountFormatter formatter = new DefaultAmountFormatter();

	@Test
	@DisplayName("Locale과 숫자를 받아 해당 Locale의 통화 형식으로 변환합니다.")
	void formattingSuccessTest() {
		assertEquals("₩123,457", formatter.format(Locale.KOREA, 123456.789));
		assertEquals("$123,456.79", formatter.format(Locale.US, 123456.789));
		assertEquals("￥123,457", formatter.format(Locale.JAPAN, 123456.789));
	}

	@Test
	@DisplayName("잘못된 입력값이 들어왔을 때 예외 발생")
	void formattingFailureTest() {
		assertThrows(IllegalArgumentException.class, () -> formatter.format(Locale.KOREA, ""));
		assertThrows(IllegalArgumentException.class, () -> formatter.format(null, 123456.79));
		assertThrows(IllegalArgumentException.class, () -> formatter.format(Locale.KOREA, null));
		assertThrows(IllegalArgumentException.class, () -> formatter.format(null, null));
	}
}
