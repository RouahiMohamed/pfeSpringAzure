package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "architecture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Architecture {
    @Id
    private String id;
    private String name;
    private Date dateCreation;
    @DBRef
    private Set<ResourceGroup> resourceGroups = new HashSet<>();
    @DBRef
    private Set<Vmss> vmsses = new HashSet<>();
    @DBRef
    private Set<VirtualMachine> virtualMachines = new HashSet<>();
    @DBRef
    private Set<VirtualNetwork> virtualNetworks = new HashSet<>();
    @DBRef
    private Set<ApplicationGateway> applicationGateways = new HashSet<>();
    @DBRef
    private Set<Subnet> subnets = new HashSet<>();
    @DBRef
    private User user;






}
