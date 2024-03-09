package com.example.mpl.repository;

import com.example.mpl.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    public Users findByUsername(String username);

    public Integer deleteByUsername(String username);
}
