package edu.poly.assignment_java6.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.assignment_java6.model.DiaChiUser;
import edu.poly.assignment_java6.model.Users;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChiUser, Long> {
     List<DiaChiUser> findByUser_IdUser(String idUser);
        Optional<Users> findByIdUser(Long id); 

  //   List<DiaChiUser> findByUser_IdUser(String idUser);


}
    