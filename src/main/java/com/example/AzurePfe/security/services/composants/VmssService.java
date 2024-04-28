package com.example.AzurePfe.security.services.composants;

import com.example.AzurePfe.models.composant.Vmss;
import com.example.AzurePfe.repository.composants.VmssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VmssService {

    @Autowired
    private VmssRepository vmssRepository;

    public Vmss createVmss(Vmss vmss) {
        return vmssRepository.save(vmss);
    }

    public Vmss getVmssById(String id) {
        return vmssRepository.findById(id).orElse(null);
    }

    public List<Vmss> getAllVmss() {
        return vmssRepository.findAll();
    }
    public void deleteVmssById(String id) {
        vmssRepository.deleteById(id);
    }

}
