package subway.domain.line.strategy;

import java.util.List;

import subway.domain.line.Section;
import subway.domain.line.command.Result;
import subway.domain.line.command.SectionInsertCommand;
import subway.domain.line.command.SectionRemoveCommand;
import subway.error.exception.SectionConnectionException;

public final class TerminalStrategy extends SectionStrategy {

	TerminalStrategy(final List<Section> sections) {
		super(sections);
	}

	@Override
	public Result addStation(final Section newSection) {
		validateCrossConnected(newSection);
		return new Result(List.of(new SectionInsertCommand(newSection)));
	}

	@Override
	public Result removeStation() {
		return new Result(List.of(new SectionRemoveCommand(sections.get(0))));
	}

	private void validateCrossConnected(final Section newSection) {
		if (newSection.isCrossConnected(upLineTerminal(), downLineTerminal())) {
			throw new SectionConnectionException("순환 노선입니다.");
		}
	}
}
