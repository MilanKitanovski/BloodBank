package org.example.repository;

import org.example.model.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentreRepository extends JpaRepository<Centre,Integer> {
    public List<Centre> findCentreByName(String name);
    @Query("SELECT sa from Centre sa where LOWER(sa.name) like ?1% or LOWER(sa.address) like ?1%")
    List<Centre> searchCentreByNameOrAddress(String search);

}
