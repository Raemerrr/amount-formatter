package io.github.raemerrr;

import java.util.Locale;

public interface AmountFormatter {
	/**
	 * Locale과 숫자를 받아 해당 Locale의 통화 형식으로 변환합니다.
	 *
	 * @param locale Locale 객체 (예: Locale.KOREA, Locale.US, Locale.JAPAN)
	 * @param amount 변환할 금액 (숫자 타입이나 숫자로 이루어진 문자열)
	 * @return 해당 Locale의 통화 형식으로 변환된 문자열
	 * @throws IllegalArgumentException 잘못된 입력값이 들어왔을 때 예외 발생
	 */
	String format(Locale locale, Object amount) throws IllegalArgumentException;
}
