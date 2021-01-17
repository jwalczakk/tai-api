package pl.polsl.tai.flappyfish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.tai.flappyfish.dto.ResultDto;
import pl.polsl.tai.flappyfish.service.ResultsService;

@RestController
public class ResultsController {

  private final ResultsService resultsService;

  @Autowired
  public ResultsController(final ResultsService resultsService) {
    this.resultsService = resultsService;
  }

  @CrossOrigin
  @PostMapping("/results")
  public ResponseEntity<List<ResultDto>> insertNewResult(@RequestBody ResultDto result) {
    resultsService.insertResult(result);
    return ResponseEntity.ok(resultsService.getSpecifiedResults(new HashMap<>()));
  }

  @CrossOrigin
  @GetMapping("/results")
  public ResponseEntity<List<ResultDto>> getAllResults(@RequestParam Map<String, String> params) {
    if (params.isEmpty()) {
      return ResponseEntity.ok(resultsService.getAllResults());
    }
    return ResponseEntity.ok(resultsService.getSpecifiedResults(params));
  }
}
