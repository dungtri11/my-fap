package com.portal.fap.repository;

import com.portal.fap.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByUsernameOrEmail(String username, String email);
}
