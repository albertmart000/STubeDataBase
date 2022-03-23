package com.example.stubedatabase;

import org.springframework.stereotype.Service;

@Service
public class STubeDataBaseService {

    private UserRepository repository;
    private VideoRepository videoRepository;
    private RatingRepository ratingRepository;
}
