package io.github.raemerrr;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import org.junit.jupiter.api.Test;

class AmountFormatterTest {

	private static final AmountFormatter DEFAULT_AMOUNT_FORMATTER = new DefaultAmountFormatter();

	@Test
	void format_shouldFormatAmountForLocale() {
		assertEquals("₩123,457", DEFAULT_AMOUNT_FORMATTER.format(Locale.KOREA, 123456.789));
		assertEquals("$123,456.79", DEFAULT_AMOUNT_FORMATTER.format(Locale.US, 123456.789));
		assertEquals("￥123,457", DEFAULT_AMOUNT_FORMATTER.format(Locale.JAPAN, 123456.789));
	}

	@Test
	void format_shouldThrowExceptionWhenLocaleIsNull() {
		var exception = assertThrows(IllegalArgumentException.class, () ->
				DEFAULT_AMOUNT_FORMATTER.format(null, 123456.789)
		);
		assertEquals("Locale and amount must not be null.", exception.getMessage());
	}

	@Test
	void format_shouldThrowExceptionWhenAmountIsNull() {
		var exception = assertThrows(IllegalArgumentException.class, () ->
				DEFAULT_AMOUNT_FORMATTER.format(Locale.US, null)
		);
		assertEquals("Locale and amount must not be null.", exception.getMessage());
	}

	@Test
	void format_shouldThrowExceptionForInvalidStringAmount() {
		var exception = assertThrows(IllegalArgumentException.class, () ->
				DEFAULT_AMOUNT_FORMATTER.format(Locale.US, "invalid amount")
		);
		assertEquals("Amount must be a valid number.", exception.getMessage());
	}

	@Test
	void format_shouldFormatIntegerAmount() {
		assertEquals("₩123,456", DEFAULT_AMOUNT_FORMATTER.format(Locale.KOREA, 123456));
	}

	@Test
	void format_shouldParseStringAmount() {
		assertEquals("$123,456.79", DEFAULT_AMOUNT_FORMATTER.format(Locale.US, "123456.789"));
	}

	@Test
	void format_shouldFormatDoubleAmount() {
		assertEquals("123 456,79 €", DEFAULT_AMOUNT_FORMATTER.format(Locale.FRANCE, 123456.789));
	}

	@Test
	void format_shouldFormatFloatAmount() {
		assertEquals("£123,456.79", DEFAULT_AMOUNT_FORMATTER.format(Locale.UK, 123456.789f));
	}
}
