package com.huisa.repository;

import com.huisa.model.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository< @NonNull Role, @NonNull Long> {

    Optional <Role> findByName(String roleUser);
}
