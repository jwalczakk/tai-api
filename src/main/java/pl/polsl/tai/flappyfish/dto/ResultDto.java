package pl.polsl.tai.flappyfish.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    private String username;
    private Long score;
    private Instant resultDate;
}
