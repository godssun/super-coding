package com.github.supercoding.respository.airplane.userPrincipal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrincipalRolesRepository extends JpaRepository<UserPrincipalRoles, Integer> {
}
