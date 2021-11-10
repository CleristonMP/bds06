package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ReviewService reviewService;
	
//	@GetMapping
//	public ResponseEntity<Page<MovieDTO>> findAll(Pageable pageable) {
//		Page<MovieDTO> page = movieService.findAllPaged(pageable);
//		return ResponseEntity.ok().body(page);
//	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = movieService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> findReviewsByMovie(@PathVariable Long movieId) {
		List<ReviewDTO> list = reviewService.findReviewsByMovie(movieId);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieMinDTO>> findByGenre(Pageable pageable,
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId) {
		Page<MovieMinDTO> page = movieService.findByGenre(pageable, genreId);
		return ResponseEntity.ok().body(page);
	}
}
