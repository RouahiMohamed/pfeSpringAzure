package com.example.AzurePfe.security.services.composants;

import com.example.AzurePfe.models.User;
import com.example.AzurePfe.models.composant.*;
import com.example.AzurePfe.repository.UserRepository;
import com.example.AzurePfe.repository.composants.ArchitectureRepository;
import com.example.AzurePfe.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class ArchitectureService {

    @Autowired
    private ArchitectureRepository architectureRepository;
    @Autowired
    private  ResourceGroupService createResourceGroup;
    @Autowired
    private SubnetService subnetService;
    @Autowired
    private VirtualMachineService virtualMachineService;
    @Autowired
    private VirtualNetworkService virtualNetworkService;
    @Autowired
    private VmssService vmssService;

    @Autowired
    private ApplicationGatewayService  applicationGatewayService;

    @Autowired
    private UserRepository userRepository;

    public Architecture createArchitecture(Architecture architecture) {
        for (ResourceGroup resourceGroup : architecture.getResourceGroups()) {
             createResourceGroup.createResourceGroup(resourceGroup);
        }
        for (VirtualMachine resourceGroup : architecture.getVirtualMachines()) {
            virtualMachineService.saveVirtualMachine(resourceGroup);
        }
        for (VirtualNetwork resourceGroup : architecture.getVirtualNetworks()) {
            virtualNetworkService.createVirtualNetwork(resourceGroup);
        }
        for (Vmss resourceGroup : architecture.getVmsses()) {
            vmssService.createVmss(resourceGroup);
        }
        for (Subnet resourceGroup : architecture.getSubnets()) {
            subnetService.createSubnet(resourceGroup);
        }
        for (ApplicationGateway resourceGroup : architecture.getApplicationGateways()) {
            applicationGatewayService.createApplicationGateway(resourceGroup);
        }
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
