package nl.nickthijssen.restserver.controllers;

import nl.nickthijssen.restserver.datamodels.Game;
import nl.nickthijssen.restserver.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/game")
public class GameController {

	@Autowired
	private GameRepository gameRepository;

	@GetMapping(path = "/{id}")
	public @ResponseBody
	Optional<Game> getGameById(@PathVariable Integer id) {
		return gameRepository.findById(id);
	}

	@GetMapping(path = "/all")
	public @ResponseBody
	Iterable<Game> getAllGames() {
		return gameRepository.findAll();
	}

	@PostMapping(path = "/")
	public @ResponseBody
	Game addNewGame(@RequestParam Date started, @RequestParam Date ended, @RequestParam String state) {
		Game newGame = new Game();
		newGame.setStarted(started);
		newGame.setEnded(ended);
		newGame.setState(state);
		gameRepository.save(newGame);
		return newGame;
	}

	@PutMapping("/{id}")
	public @ResponseBody
	Game updateGame(@RequestBody Game updatedGame, @PathVariable Integer id) {
		return gameRepository.findById(id).map(game -> {
			game.setStarted(updatedGame.getStarted());
			game.setEnded(updatedGame.getEnded());
			game.setState(updatedGame.getState());
			return gameRepository.save(game);
		}).orElseGet(() -> {
			updatedGame.setId(id);
			return gameRepository.save(updatedGame);
		});
	}

	@DeleteMapping("/{id}")
	public @ResponseBody
	void deleteGame(@PathVariable Integer id) {
		gameRepository.deleteById(id);
	}
}
