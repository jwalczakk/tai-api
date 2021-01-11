package pl.polsl.tai.flappyfish.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.tai.flappyfish.model.Result;

@Repository
public interface ResultsRepository extends JpaRepository<Result, Long> {

    Page<Result> findAllByUsername(Pageable pageable, String username);
}
