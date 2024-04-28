package com.example.AzurePfe.repository.composants;

import com.example.AzurePfe.models.composant.Architecture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArchitectureRepository extends MongoRepository<Architecture, String> {
}
