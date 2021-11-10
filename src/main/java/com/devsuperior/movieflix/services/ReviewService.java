package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	public List<ReviewDTO> findReviewsByMovie(Long movieId) {
		List<Review> list = repository.findReviewsByMovie(movieId);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		
		// getOne no Movie
		Movie movie = movieRepository.getOne(dto.getMovieId());
		
		// getOne User logado
		User user = authService.authenticated();

		entity.setText(dto.getText());
		entity.setMovie(movie);
		entity.setUser(user);
		
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}

}
