package pl.polsl.tai.flappyfish.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.tai.flappyfish.dto.ResultDto;
import pl.polsl.tai.flappyfish.model.Result;
import pl.polsl.tai.flappyfish.repo.ResultsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultsService {

    private final static Logger log = LoggerFactory.getLogger(ResultsService.class);

    private final ResultsRepository resultsRepository;

    @Autowired
    public ResultsService(final ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public void insertResult(ResultDto resultDto) {
        log.info("Inserting new result... {}", resultDto);
        Result result = mapDtoToModel(resultDto);
        resultsRepository.save(result);
    }

    public List<ResultDto> getAllResults() {
        log.info("Getting all results...");
        return resultsRepository.findAll().stream().map(this::mapModelToDto).collect(Collectors.toList());
    }

    public List<ResultDto> getSpecifiedResults() {
        log.info("Getting specified results...");
        return resultsRepository.findAll().stream().map(this::mapModelToDto).collect(Collectors.toList());
    }

    private Result mapDtoToModel(ResultDto dto) {
        return Result.builder()
                .username(dto.getUsername())
                .score(dto.getScore())
                .resultDate(dto.getResultDate())
                .build();
    }

    private ResultDto mapModelToDto(Result result) {
        return ResultDto.builder()
                .username(result.getUsername())
                .score(result.getScore())
                .resultDate(result.getResultDate())
                .build();
    }
}
