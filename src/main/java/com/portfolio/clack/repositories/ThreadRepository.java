package com.portfolio.clack.repositories;

import com.portfolio.clack.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

}
