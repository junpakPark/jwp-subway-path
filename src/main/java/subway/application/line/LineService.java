package subway.application.line;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import subway.persistence.dao.LineDao;
import subway.persistence.entity.LineEntity;
import subway.ui.dto.LineRequest;
import subway.ui.dto.LineResponse;

@Service
public class LineService {
	private final LineDao lineDao;

	public LineService(LineDao lineDao) {
		this.lineDao = lineDao;
	}

	public LineResponse saveLine(LineRequest request) {
		LineEntity persistLine = lineDao.insert(new LineEntity(request.getName(), request.getColor()));
		return LineResponse.of(persistLine);
	}

	public List<LineResponse> findLineResponses() {
		List<LineEntity> persistLines = findLines();
		return persistLines.stream()
			.map(LineResponse::of)
			.collect(Collectors.toList());
	}

	public List<LineEntity> findLines() {
		return lineDao.findAll();
	}

	public LineResponse findLineResponseById(Long id) {
		LineEntity persistLine = findLineById(id);
		return LineResponse.of(persistLine);
	}

	public LineEntity findLineById(Long id) {
		return lineDao.findById(id);
	}

	public void updateLine(Long id, LineRequest lineUpdateRequest) {
		lineDao.update(new LineEntity(id, lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
	}

	public void deleteLineById(Long id) {
		lineDao.deleteById(id);
	}

}