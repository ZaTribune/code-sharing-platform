package platform.database.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import platform.database.Code;

import java.util.List;


public interface CodeRepository extends JpaRepository<Code, String> {

    @Query("select c from Code c where c.time=-1 and c.views=-1 order by c.date desc")
    List<Code> getLatestCodes(Pageable pageable);
}
