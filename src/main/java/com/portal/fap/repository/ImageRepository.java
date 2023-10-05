package com.portal.fap.repository;

import com.portal.fap.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    public Optional<Image> findByName(String filename);
    public Optional<Image> findByPath(String pathname);
}
