package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    public User findUserByEmail(String email);
    public User findById(int id);
    public Optional<User> deleteById(int id);
    public User findUserByName(String email);
    public User getByEmail(String email);
    Optional<User> findOneByEmail(String email);
}
