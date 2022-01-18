package nl.novi.TechItEasy.repositories;

import nl.novi.TechItEasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
