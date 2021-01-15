package pl.polsl.tai.flappyfish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.polsl.tai.flappyfish.dto.ResultDto;
import pl.polsl.tai.flappyfish.model.Result;
import pl.polsl.tai.flappyfish.repo.ResultsRepository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ResultsService {

  private final ResultsRepository resultsRepository;

  @Autowired
  public ResultsService(final ResultsRepository resultsRepository) {
    this.resultsRepository = resultsRepository;
  }

  @PostConstruct
  public void prepareData() {
    for (int i = 1; i <= 10; i++) {
      String username = (i % 2 == 0) ? "champion" : ("user" + i);
      Result result = Result.builder()
          .id((long) i)
          .score((long) new Random().nextInt(1000))
          .username(username)
          .resultDate(Instant.now())
          .build();
      resultsRepository.save(result);
    }
  }

  public void insertResult(ResultDto resultDto) {
    Result result = mapDtoToModel(resultDto);
    resultsRepository.save(result);
  }

  public List<ResultDto> getAllResults() {
    return resultsRepository.findAll().stream().map(this::mapModelToDto).collect(Collectors.toList());
  }

  public List<ResultDto> getSpecifiedResults(Map<String, String> params) {
    String sortOrder = "DESC";
    if (params.containsKey("sort") && params.get("sort").equalsIgnoreCase("ASC")) {
      sortOrder = "ASC";
    }

    int top = 10;
    if (params.containsKey("top")) {
      top = Integer.parseInt(params.get("top"));
    }

    final Sort sortBy = Sort.by(sortOrder.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "score");
    Pageable sortedByPriceDescNameAsc =
        PageRequest.of(0, top, sortBy);

    Page<Result> all;
    if (params.containsKey("username")) {
      all = resultsRepository.findAllByUsername(sortedByPriceDescNameAsc, params.get("username"));
    } else {
      all = resultsRepository.findAll(sortedByPriceDescNameAsc);
    }
    return all.get().map(this::mapModelToDto).collect(Collectors.toList());
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
