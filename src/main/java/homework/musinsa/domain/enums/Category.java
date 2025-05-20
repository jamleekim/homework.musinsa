package homework.musinsa.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Category {
    TOP(1, "상의"),
    OUTER(2, "아우터"),
    PANTS(3, "바지"),
    SNEAKERS(4, "스니커즈"),
    BAG(5, "가방"),
    HAT(6, "모자"),
    SOCKS(7, "양말"),
    ACCESSORY(8, "액세서리");

    private final int rank;
    private final String displayName;

    /**
     * displayName으로부터 enum 상수를 찾아 반환합니다.
     * 일치하는 값이 없으면 IllegalArgumentException을 발생시킵니다.
     */
    public static Category fromDisplayName(String displayName) {
        return Arrays.stream(values())
                .filter(c -> c.displayName.equals(displayName))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown Category displayName: " + displayName)
                );
    }

    @JsonValue
    @Override
    public String toString() {
        return displayName;
    }
}