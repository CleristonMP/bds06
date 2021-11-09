package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	public List<ReviewDTO> findReviewsByMovie(Long movieId) {
		List<Review> list = repository.findReviewsByMovie(movieId);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}

}
