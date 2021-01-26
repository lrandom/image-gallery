package com.niit.edu.imagegallery.respositories;

import com.niit.edu.imagegallery.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageJpa
        extends CrudRepository<Image, Long> {
    @Override
    Iterable<Image> findAll();

    @Override
    Optional<Image> findById(Long aLong);
}
