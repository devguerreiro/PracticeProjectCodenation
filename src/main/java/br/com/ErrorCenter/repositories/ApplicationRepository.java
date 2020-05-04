package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    Optional<ApplicationEntity> findByEmail(String email);

}
