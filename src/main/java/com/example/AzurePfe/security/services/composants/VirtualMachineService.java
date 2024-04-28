package com.example.AzurePfe.security.services.composants;

import com.example.AzurePfe.models.composant.VirtualMachine;
import com.example.AzurePfe.repository.composants.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VirtualMachineService {
    @Autowired
    private VirtualMachineRepository virtualMachineRepository;

    public List<VirtualMachine> getAllVirtualMachines() {
        return virtualMachineRepository.findAll();
    }

    public Optional<VirtualMachine> getVirtualMachineById(String id) {
        return virtualMachineRepository.findById(id);
    }

    public VirtualMachine saveVirtualMachine(VirtualMachine virtualMachine) {
        return virtualMachineRepository.save(virtualMachine);
    }

    public void deleteVirtualMachineById(String id) {
        virtualMachineRepository.deleteById(id);
    }
}
