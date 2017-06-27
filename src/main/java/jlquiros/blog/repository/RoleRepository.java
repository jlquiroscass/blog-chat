package jlquiros.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jlquiros.blog.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}