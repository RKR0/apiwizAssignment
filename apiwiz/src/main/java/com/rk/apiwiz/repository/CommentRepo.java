package com.rk.apiwiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.apiwiz.model.Comment;


@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
