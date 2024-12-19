package com.example.domainmysql.domains.user.repository;

import com.example.domainmysql.domains.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
