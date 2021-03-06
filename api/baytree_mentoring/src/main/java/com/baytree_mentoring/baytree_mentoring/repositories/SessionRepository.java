package com.baytree_mentoring.baytree_mentoring.repositories;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}

