package com.example.AzurePfe.controllers.composants;

import com.example.AzurePfe.models.composant.Architecture;
import com.example.AzurePfe.security.services.composants.ArchitectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/architectures")
public class ArchitectureController {

    @Autowired
    private ArchitectureService architectureService;

    @GetMapping("/getAll")
    public List<Architecture> getAllArchitectures() {
        return architectureService.getAllArchitectures();
    }

    @GetMapping("/getById/{id}")
    public Architecture getArchitectureById(@PathVariable String id) {
        return architectureService.getArchitectureById(id);
    }

    @PostMapping("/add")
    public Architecture createArchitecture(@RequestBody Architecture architecture) {
        return architectureService.createArchitecture(architecture);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArchitecture(@PathVariable String id) {
        architectureService.deleteArchitecture(id);
    }
}
