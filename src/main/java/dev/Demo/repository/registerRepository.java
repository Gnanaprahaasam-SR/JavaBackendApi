package dev.Demo.repository;

import dev.Demo.model.register;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface registerRepository extends MongoRepository<register, String> {
    @Query("{'userName': ?0}")
    Optional<register> findByUserName(String userName );
}
