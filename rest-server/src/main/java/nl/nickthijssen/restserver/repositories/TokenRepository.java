package nl.nickthijssen.restserver.repositories;

import nl.nickthijssen.restserver.datamodels.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

	Optional<Token> findByUsername(String username);

	Optional<Token> findByUuid(String uuid);
}
