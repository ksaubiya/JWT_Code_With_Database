package comjwtdb.Repository;

import comjwtdb.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {

    //custom method

    Optional<RefreshToken> findByRefreshToken(String token);
}
