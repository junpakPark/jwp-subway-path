package subway.domain.line.strategy;

import java.util.List;

import subway.domain.line.Section;
import subway.domain.line.command.Result;

public abstract class SectionStrategy {

	protected final List<Section> sections;

	protected SectionStrategy(final List<Section> sections) {
		this.sections = sections;
	}

	public abstract Result addStation(final Section newSection);

	public abstract Result removeStation();

	public final Section upLineTerminal() {
		return sections.get(0);
	}

	public final Section downLineTerminal() {
		return sections.get(sections.size() - 1);
	}
}
