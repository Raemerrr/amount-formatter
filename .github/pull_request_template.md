# 🔥 새로운 릴리스: v{{버전}}

안녕하세요!🙋🏻‍♂️ `amount-formatter`의 최신 버전 **v{{버전}}**이 릴리스 되었습니다.
🎉 이번 릴리스에서는 다음과 같은 기능 추가와 개선 사항이 포함되어 있습니다.

---

## 🚀 주요 변경 사항
- 💳 **새로운 통화 지원**: JPY(엔), EUR(유로), KRW(원) 등 다양한 통화 형식 추가
- 🔧 **커스터마이징 옵션**: 사용자 지정 통화 기호 및 소수점 자리 설정 기능
- 🌟 **퍼포먼스 개선**: 대량 데이터 처리 속도 20% 향상
- 🐛 **버그 수정**: 일부 환경에서 포매팅 오류가 발생하던 문제 해결

---

## 📦 설치 및 사용법
### **Gradle**
```gradle
implementation("io.github.raemerrr:amount-formatter:{{버전}}")
```

### 간단한 사용 예시
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
---

## 🛠️ 피드백
- 기능 요청이나 버그 리포트는 [Issues](https://github.com/Raemerrr/amount-formatter/issues)에 남겨주세요!
- 문서화가 부족하거나 개선이 필요한 부분이 있다면 언제든지 PR을 보내주세요.
---

## ❤️ 감사합니다.