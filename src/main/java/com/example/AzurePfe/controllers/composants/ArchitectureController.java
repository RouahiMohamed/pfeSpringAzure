package com.example.AzurePfe.controllers.composants;

import com.example.AzurePfe.models.composant.*;
import com.example.AzurePfe.models.ressources.Region;
import com.example.AzurePfe.security.services.composants.ArchitectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Architecture> updateArchitecture(@PathVariable String id, @RequestBody Architecture updatedArchitecture) {
        Architecture architecture = architectureService.updateArchitecture(id, updatedArchitecture);
        if (architecture != null) {
            return ResponseEntity.ok(architecture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getById/{id}")
    public Architecture getArchitectureById(@PathVariable String id) {
        return architectureService.getArchitectureById(id);
    }

    @PostMapping("/add")
    public Architecture createArchitecture(@RequestBody Architecture architecture) {

        return architectureService.createArchitecture(architecture);
    }
    @PostMapping("/generate-terraform-code")
    public String generateTerraformCode(@RequestBody Architecture architecture) {
        StringBuilder terraformCode = new StringBuilder();
        terraformCode.append("provider \"azurerm\" {\n")
                .append("  features {}\n")
                .append("}\n\n");

        // Générer le code pour les groupes de ressources
        for (ResourceGroup resourceGroup : architecture.getResourceGroups()) {
            terraformCode.append("resource \"azurerm_resource_group\" \"").append(resourceGroup.getName()).append("\" {\n")
                    .append("  name     = \"").append(resourceGroup.getName()).append("\"\n")
                    .append("  location = \"").append(resourceGroup.getRegion().getName()).append("\"\n")
                    .append("}\n\n");
        }

        // Générer le code pour les réseaux virtuels
        for (VirtualNetwork virtualNetwork : architecture.getVirtualNetworks()) {
            terraformCode.append("resource \"azurerm_virtual_network\" \"").append(virtualNetwork.getName()).append("\" {\n")
                    .append("  name                = \"").append(virtualNetwork.getName()).append("\"\n")
                    .append("  resource_group_name = azurerm_resource_group.").append(virtualNetwork.getResourceGroup().getName()).append(".name\n")
                    .append("  location            = \"").append(virtualNetwork.getRegion().getName()).append("\"\n")
                    .append("  address_space       = [\"").append(virtualNetwork.getIpAddresses()).append("\"]\n")
                    .append("}\n\n");
        }

        // Générer le code pour les sous-réseaux
        for (Subnet subnet : architecture.getSubnets()) {
            terraformCode.append("resource \"azurerm_subnet\" \"").append(subnet.getName()).append("\" {\n")
                    .append("  name                 = \"").append(subnet.getName()).append("\"\n")
                    .append("  virtual_network_name = azurerm_virtual_network.").append(subnet.getVirtualNetworks()).append(".name\n")
                    .append("  address_prefixes     = [\"").append(subnet.getAdress()).append("\"]\n")
                    .append("}\n\n");
        }

        // Générer le code pour les passerelles d'applications
        for (ApplicationGateway appGateway : architecture.getApplicationGateways()) {
            terraformCode.append("resource \"azurerm_application_gateway\" \"").append(appGateway.getName()).append("\" {\n")
                    .append("  name                = \"").append(appGateway.getName()).append("\"\n")
                    .append("  resource_group_name = azurerm_resource_group.").append(appGateway.getResourceGroupe().getName()).append(".name\n")
                    .append("  location            = \"").append(appGateway.getRegion().getName()).append("\"\n")
                    .append("  sku                 = \"Standard_v2\"\n")
                    .append("  autoscale_configuration {\n")
                    .append("    min_capacity = ").append(appGateway.getMinimum_Instance_Count()).append("\n")
                    .append("    max_capacity = ").append(appGateway.getMaximum_Instance_Count()).append("\n")
                    .append("  }\n")
                    .append("}\n\n");
        }

        // Générer le code pour les machines virtuelles
        for (VirtualMachine vm : architecture.getVirtualMachines()) {
            terraformCode.append("resource \"azurerm_virtual_machine\" \"").append(vm.getName()).append("\" {\n")
                    .append("  name                  = \"").append(vm.getName()).append("\"\n")
                    .append("  resource_group_name   = azurerm_resource_group.").append(vm.getResourceGroupe().getName()).append(".name\n")
                    .append("  location              = \"").append(vm.getRegion().getName()).append("\"\n")
                    .append("  vm_size               = \"Standard_DS1_v2\"\n")
                    .append("  network_interface_ids = [azurerm_network_interface.").append(vm.getSubnet().getName()).append(".id]\n")
                    .append("  delete_os_disk_on_termination = true\n")
                    .append("  delete_data_disks_on_termination = true\n")
                    .append("  os_profile {\n")
                    .append("    computer_name  = \"").append(vm.getName()).append("\"\n")
                    .append("    admin_username = \"").append(vm.getUsername()).append("\"\n")
                    .append("    admin_password = \"").append(vm.getPassword()).append("\"\n")
                    .append("  }\n")
                    .append("  storage_image_reference {\n")
                    .append("    publisher = \"").append(vm.getIdImage().getPublisher()).append("\"\n")
                    .append("    offer     = \"").append(vm.getIdImage().getOffer()).append("\"\n")
                    .append("    sku       = \"").append(vm.getIdImage().getSku()).append("\"\n")
                    .append("    version   = \"").append(vm.getIdImage().getVersion()).append("\"\n")
                    .append("  }\n")
                    .append("  os_disk {\n")
                    .append("    storage_account_type = \"Standard_LRS\"\n")
                    .append("    disk_size_gb         = ").append(vm.getIdDiskSize().getOsDiskSizeInMB() / 1024).append("\n")
                    .append("  }\n")
                    .append("}\n\n");
        }

        // Générer le code pour les ensembles de machines virtuelles (VMSS)
        for (Vmss vmss : architecture.getVmsses()) {
            terraformCode.append("resource \"azurerm_linux_virtual_machine_scale_set\" \"").append(vmss.getName()).append("\" {\n")
                    .append("  name                = \"").append(vmss.getName()).append("\"\n")
                    .append("  resource_group_name = azurerm_resource_group.").append(vmss.getVirtualMachine().getResourceGroupe().getName()).append(".name\n")
                    .append("  location            = \"").append(vmss.getVirtualMachine().getRegion().getName()).append("\"\n")
                    .append("  sku                 {\n")
                    .append("    capacity = ").append(vmss.getNb_vm()).append("\n")
                    .append("    tier     = \"Standard\"\n")
                    .append("    size     = \"Standard_DS1_v2\"\n")
                    .append("  }\n")
                    .append("  upgrade_policy_mode = \"Automatic\"\n")
                    .append("}\n\n");
        }

        return terraformCode.toString();
    }

    @PostMapping("/generate-pulumi-code")
    public String generatePulumiCode(@RequestBody Architecture architecture) {
        StringBuilder pulumiCode = new StringBuilder();
        pulumiCode.append("import * as pulumi from '@pulumi/pulumi';\n");
        pulumiCode.append("import * as azure from '@pulumi/azure';\n\n");
        // Générer le code pour le groupe de ressources
        for (ResourceGroup resourceGroup : architecture.getResourceGroups()) {
            pulumiCode.append("const ").append(resourceGroup.getName()).append(" = new azure.core.ResourceGroup('")
                    .append(resourceGroup.getName()).append("', {\n")
                    .append("    location: '").append(resourceGroup.getRegion().getName()).append("',\n")
                    .append("});\n\n");
        }

        for (VirtualNetwork virtualNetwork : architecture.getVirtualNetworks()) {
            pulumiCode.append("const ").append(virtualNetwork.getName()).append(" = new azure.network.VirtualNetwork('")
                    .append(virtualNetwork.getName()).append("', {\n");
            if (virtualNetwork.getResourceGroup() != null){
                pulumiCode.append("    resourceGroupName: '").
                        append(virtualNetwork.getResourceGroup().getName()).append("',\n");}
            pulumiCode.append("    location: '").append(virtualNetwork.getRegion().getName()).append("',\n")
                    .append("    addressSpaces: ['").append(virtualNetwork.getIpAddresses()).append("'],\n")
                    .append("});\n");
        }

        //SUBNETTT
        for (Subnet subnet : architecture.getSubnets()) {

                pulumiCode.append("const ").append(subnet.getName()).append(" = new azure.network.Subnet('")
                        .append(subnet.getName()).append("', {\n")
                        .append("    addressPrefix: ['").append(subnet.getAdress()).append("'],\n");
                if (subnet.getVirtualNetworks() != null) {
                    pulumiCode.append("    virtualNetworkName: ").append(subnet.getVirtualNetworks()).append(",\n");
                }
                pulumiCode.append("});\n");

        }

//APPLICATIONgATEWAY
        for (ApplicationGateway appGateway : architecture.getApplicationGateways()) {
            pulumiCode.append("const ").append(appGateway.getName()).append(" = new azure.network.ApplicationGateway('").append(appGateway.getName()).append("', {\n");
            if (appGateway.getResourceGroupe() != null){
                pulumiCode.append("    resourceGroupName: '").append(appGateway.getResourceGroupe().getName()).append("',\n");}
            if (appGateway.getSubnet() != null) {
                pulumiCode.append("    subnet: '").append(appGateway.getSubnet().getName()).append("',\n");}
            pulumiCode.append("    location: ").append(appGateway.getRegion().getName()).append(",\n")
                    .append("    autoscaleConfiguration: {\n")
                    .append("        minCapacity: ").append(appGateway.getMinimum_Instance_Count()).append(",\n")
                    .append("        maxCapacity: ").append(appGateway.getMaximum_Instance_Count()).append(",\n")
                    .append("    },\n")
                    .append("});\n");
        }

        // vm
        for (VirtualMachine vm : architecture.getVirtualMachines()) {
            pulumiCode.append("const ").append(vm.getName()).append(" = new azure.compute.VirtualMachine('").append(vm.getName()).append("', {\n");
            if (vm.getResourceGroupe() != null){
                pulumiCode.append("    resourceGroupName: '").append(vm.getResourceGroupe().getName()).append("',\n");}

                    pulumiCode.append("    location: ").append(vm.getRegion().getName()).append(",\n")
                    .append("    osProfile: {\n")
                    .append("        computerName: '").append(vm.getName()).append("',\n")
                    .append("        adminUsername: '").append(vm.getUsername()).append("',\n")
                    .append("        adminPassword: pulumi.secret('").append(vm.getPassword()).append("'),\n")
                    .append("    },\n")
                    .append("    storageProfile: {\n")
                    .append("        imageReference: {\n")
                    .append("            publisher: '").append(vm.getIdImage().getPublisher()).append("',\n")
                    .append("            offer: '").append(vm.getIdImage().getOffer()).append("',\n")
                    .append("            sku: '").append(vm.getIdImage().getSku()).append("',\n")
                    .append("            version: '").append(vm.getIdImage().getVersion()).append("',\n")
                    .append("            architecture: '").append(vm.getIdImage().getArchitecture()).append("',\n")
                    .append("            urnAlias: '").append(vm.getIdImage().getUrnAlias()).append("',\n")
                    .append("            urn: '").append(vm.getIdImage().getUrn()).append("',\n")
                    .append("        },\n")
                    .append("        osDisk: {\n")
                    .append("            storageAccountType: '").append(vm.getIdDiskSize().getName()).append("',\n")
                    .append("        },\n")
                    .append("        dataDisks: [{\n")
                    .append("            ResourceDiskSizeInGB: ").append(vm.getIdDiskSize().getResourceDiskSizeInMB() / 1024).append(",\n")
                    .append("            OsDiskSizeGB: ").append(vm.getIdDiskSize().getOsDiskSizeInMB() / 1024).append(",\n")
                    .append("            MemoryInMB: ").append(vm.getIdDiskSize().getMemoryInMB() / 1024).append(",\n")
                    .append("            NumberOfCores: ").append(vm.getIdDiskSize().getNumberOfCores() ).append(",\n")
                    .append("            MaxDataDiskCount: ").append(vm.getIdDiskSize().getMaxDataDiskCount() ).append(",\n")
                    .append("        }],\n")
                    .append("    },\n")
                    .append("    networkProfile: {\n");
            if (vm.getSubnet() != null) {
                pulumiCode.append("        networkInterfaces: [{\n")
                    .append("    subnet: '").append(vm.getSubnet().getName()).append("',\n");}
                    pulumiCode
                    .append("        }],\n")
                    .append("    },\n")
                    .append("});\n");
        }

        for (Vmss vmss : architecture.getVmsses()) {
            pulumiCode.append("const ").append(vmss.getName()).append(" = new azure.compute.ScaleSet('").append(vmss.getName()).append("', {\n")
                    .append("    virtual machine: ").append(vmss.getVirtualMachine().getName()).append(",\n")
                    .append("    Number: ").append(vmss.getNb_vm()).append(",\n")
                    .append("});\n");
        }
        return pulumiCode.toString();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteArchitecture(@PathVariable String id) {
        architectureService.deleteArchitecture(id);
    }
}
