package io.github.raemerrr;

import java.text.NumberFormat;
import java.util.Locale;

public class DefaultAmountFormatter implements AmountFormatter {

	@Override
	public String format(Locale locale, Object amount) throws IllegalArgumentException {
		if (locale == null || amount == null) {
			throw new IllegalArgumentException("Locale and amount must not be null.");
		}
		var amountFormat = NumberFormat.getCurrencyInstance(locale);
		var parsedAmount = parseAmount(amount);

		return amountFormat.format(parsedAmount);
	}

	private double parseAmount(Object amount) throws IllegalArgumentException {
		try {
			if (amount instanceof Number number) {
				return number.doubleValue();
			} else {
				return Double.parseDouble(amount.toString());
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Amount must be a valid number.");
		}
	}

	public static void main(String[] args) throws IllegalArgumentException {
		AmountFormatter formatter = new DefaultAmountFormatter();

		System.out.println(formatter.format(Locale.KOREA, 123456.789));
		System.out.println(formatter.format(Locale.US, 123456.789));
		System.out.println(formatter.format(Locale.JAPAN, 123456.789));
	}
}
