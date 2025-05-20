package homework.musinsa.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

	@Nested
	class fromDisplayNameTest {

		@ParameterizedTest
		@CsvSource({
				"상의, TOP",
				"아우터, OUTER",
				"바지, PANTS",
				"스니커즈, SNEAKERS",
				"가방, BAG",
				"모자, HAT",
				"양말, SOCKS",
				"액세서리, ACCESSORY"
		})
		void should_return_corresponding_korean_name(String displayName, String expectedCategoryName) {
			Category expectedCategory = Category.valueOf(expectedCategoryName);
			Category actualCategory = Category.fromDisplayName(displayName);
			assertThat(actualCategory, is(equalTo(expectedCategory)));
		}

		@ParameterizedTest
		@ValueSource(strings = {"", " ", "없는카테고리", " 상의 "})
		void should_throw_IllegalArgument_exception_for_invalid_displayName(String invalidDisplayName) {
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
				Category.fromDisplayName(invalidDisplayName);
			});
			assertThat(exception.getMessage(), is(equalTo("Unknown Category displayName: " + invalidDisplayName)));
		}

	}

	@Nested
	class toStringTest {

		@ParameterizedTest
		@CsvSource({
				"TOP, 상의",
				"OUTER, 아우터",
				"PANTS, 바지",
				"SNEAKERS, 스니커즈",
				"BAG, 가방",
				"HAT, 모자",
				"SOCKS, 양말",
				"ACCESSORY, 액세서리"
		})
		void should_return_display_name_due_to_annotation(String categoryName, String expectedDisplayName) {
			Category category = Category.valueOf(categoryName);
			assertThat(category.toString(), is(equalTo(expectedDisplayName)));
		}
	}

	@Nested
	class RankTest {

		@ParameterizedTest
		@CsvSource({
				"TOP, 1",
				"OUTER, 2",
				"PANTS, 3",
				"SNEAKERS, 4",
				"BAG, 5",
				"HAT, 6",
				"SOCKS, 7",
				"ACCESSORY, 8"
		})
		void should_return_correct_rank_value(String categoryName, int expectedRank) {
			Category category = Category.valueOf(categoryName);
			assertThat(category.getRank(), is(equalTo(expectedRank)));
		}
	}

}