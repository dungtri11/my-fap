package com.portal.fap.repository;

import com.portal.fap.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByUsername(String username);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    public Optional<Account> findByEmail(String email);
}
