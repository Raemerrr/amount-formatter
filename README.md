# 🌍 Amount Formatter 라이브러리

**AmountFormatter**는 국가별 화폐 형식화를 간단하게 처리할 수 있는 라이브러리입니다.  
숫자 데이터를 국가/지역에 맞는 통화 표현으로 변환할 수 있습니다.

---

## ✨ 주요 기능

- 국가별 통화 형식 지원 (₩, $, €, ¥ 등)
- Java 표준 `Locale`과 연동
- 숫자 또는 숫자로 이루어진 문자열을 자동 변환
- 안전하고 확장 가능한 설계

---

## 🛠️ 설치 방법

### 1. Maven

```xml

<dependency>
	<groupId>io.github.raemerrr</groupId>
	<artifactId>amount-formatter</artifactId>
	<version>1.0.0</version>
</dependency>
```

### 2. Gradle

```gradle
implementation("io.github.raemerrr:amount-formatter:1.0.0")
```

# 🚀 AmountFormatter 사용 방법

## 1. **AmountFormatter 사용하기**

### 기본 사용법

```java
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		AmountFormatter formatter = new DefaultAmountFormatter();

		System.out.println(formatter.format(Locale.KOREA, 1234567.89)); // ₩1,234,568
		System.out.println(formatter.format(Locale.US, "1234567.89")); // $1,234,567.89
		System.out.println(formatter.format(Locale.JAPAN, 1234567));   // ￥1,234,567
	}
}
```

### 결과 예시

```text
₩1,234,568
$1,234,567.89
￥1,234,567
```

## 2. 커스텀 포맷터 만들기

특정 국가의 독자적인 규칙을 적용하려면 클래스를 확장하세요:

```java
public class CustomAmountFormatter extends DefaultAmountFormatter {

	@Override
	public String format(Locale locale, Object amount) {
		String result = super.format(locale, amount);
		return result + " (Custom Rule)";
	}
}
```

결과 예시

```text
₩1,234,568 (Custom Rule)
```

### 🖤 FAQ

* Q. 문자열로 금액을 넘겨도 되나요?
    * A. 네! 숫자 타입 (Number)과 숫자로 이루어진 문자열 둘 다 지원합니다.
* Q. Locale이 없으면 어떻게 하나요?
    * A. 특정 국가 코드가 있다면 new Locale("", "KR")로 변환 가능합니다.
* Q. 잘못된 입력값이 들어가면요?
    * A. IllegalArgumentException을 던져 잘못된 값을 알려줍니다.

## 📜 License

This project is licensed under the [MIT License](LICENSE).  
Feel free to use, modify, and distribute it as per the license terms.

© 2024 [Raemerrr]. All rights reserved.
