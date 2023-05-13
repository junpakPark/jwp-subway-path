package subway.application.station;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import subway.persistence.dao.StationDao;
import subway.persistence.entity.StationEntity;
import subway.ui.dto.StationRequest;
import subway.ui.dto.StationResponse;

@Service
@Transactional
public class StationService {
	private final StationDao stationDao;

	public StationService(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public StationResponse saveStation(StationRequest stationRequest) {
		StationEntity station = stationDao.insert(new StationEntity(stationRequest.getName()));
		return StationResponse.of(station);
	}

	public StationResponse findStationResponseById(Long id) {
		return StationResponse.of(stationDao.findById(id));
	}

	public List<StationResponse> findAllStationResponses() {
		List<StationEntity> stations = stationDao.findAll();

		return stations.stream()
			.map(StationResponse::of)
			.collect(Collectors.toList());
	}

	public void updateStation(Long id, StationRequest stationRequest) {
		stationDao.update(new StationEntity(id, stationRequest.getName()));
	}

	public void deleteStationById(Long id) {
		stationDao.deleteById(id);
	}
}
