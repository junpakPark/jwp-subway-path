package subway.domain.Sections;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import subway.domain.Fixture;
import subway.domain.Section;

class EmptySectionsTest {

	@Test
	@DisplayName("새로운 역 추가 시, 리스트로 감싸서 반환한다")
	void addStationTest() {
		// given
		final Section addSection = Fixture.NEW_SECTION;
		final StationAddable sections = SectionsFactory.createForAdd(Collections.emptyList(), addSection);

		// when
		final List<Section> actual = sections.addStation(addSection);

		// then
		assertThat(actual).hasSize(1);
		assertThat(actual.get(0)).isEqualTo(addSection);
	}

	@Test
	@DisplayName("기존 역 제거 시, 역이 포함된 구간이 없어서 예외가 발생한다.")
	void removeStationTest() {
		// given
		final StationRemovable sections = SectionsFactory.createForRemove(Collections.emptyList());

		// when & then
		assertThatThrownBy(sections::removeStation)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당하는 역이 없습니다.");
	}

}
