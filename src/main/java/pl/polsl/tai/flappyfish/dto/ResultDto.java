package pl.polsl.tai.flappyfish.dto;


import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

  @NotBlank(message = "Username is required.")
  private String username;
  @NotNull(message = "Score is required.")
  private Long score;
  @NotNull(message = "Result date is required.")
  private Instant resultDate;
}
