package pl.polsl.tai.flappyfish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.tai.flappyfish.dto.ResultDto;
import pl.polsl.tai.flappyfish.service.ResultsService;

import java.util.List;

@RestController
public class ResultsController {

    private final ResultsService resultsService;

    @Autowired
    public ResultsController(final ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @PostMapping("/results")
    public ResponseEntity<String> insertNewResult(@RequestBody ResultDto result) {
        resultsService.insertResult(result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/results")
    public ResponseEntity<List<ResultDto>> getAllResults() {
        return ResponseEntity.ok(resultsService.getAllResults());
    }

    @GetMapping(value = "/specified")
    public ResponseEntity<List<ResultDto>> getSpecifiedResults() {
        return ResponseEntity.ok(resultsService.getSpecifiedResults());
    }
}
