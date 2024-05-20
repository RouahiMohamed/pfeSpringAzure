package com.example.AzurePfe.security.services.composants;

import com.example.AzurePfe.models.composant.Subnet;
import com.example.AzurePfe.models.composant.VirtualNetwork;
import com.example.AzurePfe.repository.composants.SubnetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubnetService {

    @Autowired
    private SubnetRepository subnetRepository;
    @Autowired
    private VirtualNetworkService virtualNetworkService;

    public List<Subnet> getAllSubnets() {
        return subnetRepository.findAll();
    }

    public Subnet getSubnetById(String id) {
        return subnetRepository.findById(id).orElse(null);
    }

    public Subnet createSubnet(Subnet subnet) {
        
        return subnetRepository.save(subnet);
    }

    public void deleteSubnet(String id) {
        subnetRepository.deleteById(id);
    }
}
