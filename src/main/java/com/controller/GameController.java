package com.controller;

// You need to import hateoas, also as Maven dependency
// Spring Boot can do this for you

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import com.model.Game;
import com.exceptions.GameNotFoundException;
import com.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/games")
public class GameController {

	@Autowired
	GameRepository repo;

	@GetMapping("/")
	public List<Game> retrieveAllGames()
	{
		return repo.findAll();
	}


	@GetMapping("/{id}")
	public EntityModel<Game> retrieveGame(@PathVariable long id)
	{
		Optional<Game> game = repo.findById(id);
		if (!game.isPresent())
			throw new GameNotFoundException("id: " + id);

		EntityModel<Game> resource = EntityModel.of(game.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllGames());
		resource.add(linkTo.withRel("all-games"));

		Link selfLink = linkTo(methodOn(this.getClass()).retrieveGame(id)).withSelfRel();
		resource.add(selfLink);
		return resource;
	}

	@DeleteMapping("/{id}")
	public void deleteGame(@PathVariable long id) {
		repo.deleteById(id);
	}

	// Create a new resource and remember its unique location in the hypermedia
	@PostMapping("/")
	public ResponseEntity<Object> createGame(@RequestBody Game game)
	{
		Game savedGame = repo.save(game);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedGame.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateGame(@RequestBody Game game, @PathVariable long id)
	{
		Optional<Game> gameOptional = repo.findById(id);
		if (!gameOptional.isPresent())
			return ResponseEntity.notFound().build();
		game.setId(id);
		repo.save(game);
		return ResponseEntity.noContent().build();
	}
}
