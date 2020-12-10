package nl.nickthijssen.restserver.repositories;

import nl.nickthijssen.restserver.datamodels.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

}
