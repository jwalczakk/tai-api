package pl.polsl.tai.flappyfish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.tai.flappyfish.dto.ResultDto;
import pl.polsl.tai.flappyfish.service.ResultsService;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ResultDto>> getAllResults(@RequestParam Map<String, String> params) {
        if (params.isEmpty()) {
            return ResponseEntity.ok(resultsService.getAllResults());
        }
        return ResponseEntity.ok(resultsService.getSpecifiedResults(params));
    }
}
