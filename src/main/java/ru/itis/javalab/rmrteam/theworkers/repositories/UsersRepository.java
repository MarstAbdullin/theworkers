package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.entities.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmLink(String email);

    List<User> findAllByRole(Role role);

    @Query("from User user where " +
            "(:role = user.role) and (" +
            "upper(user.email) like concat('%', upper(:query), '%'))")
    Page<User> search(@Param("query") String query, @Param("role") Role role, Pageable pageable);

    @Query("update User u set u.confirmed = true where u.email = ?1")
    void confirmed(String email);

}
