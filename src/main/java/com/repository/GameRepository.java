package com.repository;

import com.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>
{
    // Notice, there are no methods here, but we still can use all those, which we inherit from JpaRepository
}
