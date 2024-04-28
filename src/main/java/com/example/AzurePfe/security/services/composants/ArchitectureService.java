package com.example.AzurePfe.security.services.composants;

import com.example.AzurePfe.models.User;
import com.example.AzurePfe.models.composant.Architecture;
import com.example.AzurePfe.repository.UserRepository;
import com.example.AzurePfe.repository.composants.ArchitectureRepository;
import com.example.AzurePfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ArchitectureService {

    @Autowired
    private ArchitectureRepository architectureRepository;

    @Autowired
    private UserRepository userRepository; // Suppose qu'il existe un repository pour l'entité User

    public Architecture createArchitecture(Architecture architecture) {
        return architectureRepository.save(architecture);
    }




    public List<Architecture> getAllArchitectures() {
        return architectureRepository.findAll();
    }

    public Architecture getArchitectureById(String id) {
        return architectureRepository.findById(id).orElse(null);
    }



    public void deleteArchitecture(String id) {
        architectureRepository.deleteById(id);
    }
}
