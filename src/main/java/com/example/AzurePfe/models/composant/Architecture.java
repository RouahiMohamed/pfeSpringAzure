package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "architecture")
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

    public Architecture(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Set<ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }

    public void setResourceGroups(Set<ResourceGroup> resourceGroups) {
        this.resourceGroups = resourceGroups;
    }

    public Set<Vmss> getVmsses() {
        return vmsses;
    }

    public void setVmsses(Set<Vmss> vmsses) {
        this.vmsses = vmsses;
    }

    public Set<VirtualMachine> getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(Set<VirtualMachine> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

    public Set<VirtualNetwork> getVirtualNetworks() {
        return virtualNetworks;
    }

    public void setVirtualNetworks(Set<VirtualNetwork> virtualNetworks) {
        this.virtualNetworks = virtualNetworks;
    }

    public Set<ApplicationGateway> getApplicationGateways() {
        return applicationGateways;
    }

    public void setApplicationGateways(Set<ApplicationGateway> applicationGateways) {
        this.applicationGateways = applicationGateways;
    }

    public Set<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(Set<Subnet> subnets) {
        this.subnets = subnets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Architecture() {
    }

    public Architecture(String id, String name, Date dateCreation, Set<ResourceGroup> resourceGroups, Set<Vmss> vmsses, Set<VirtualMachine> virtualMachines, Set<VirtualNetwork> virtualNetworks, Set<ApplicationGateway> applicationGateways, Set<Subnet> subnets) {
        this.id = id;
        this.name = name;
        this.dateCreation = dateCreation;
        this.resourceGroups = resourceGroups;
        this.vmsses = vmsses;
        this.virtualMachines = virtualMachines;
        this.virtualNetworks = virtualNetworks;
        this.applicationGateways = applicationGateways;
        this.subnets = subnets;
    }

    public Architecture(String id, String name, Date dateCreation, Set<ResourceGroup> resourceGroups, Set<Vmss> vmsses, Set<VirtualMachine> virtualMachines, Set<VirtualNetwork> virtualNetworks, Set<ApplicationGateway> applicationGateways, Set<Subnet> subnets, User user) {
        this.id = id;
        this.name = name;
        this.dateCreation = dateCreation;
        this.resourceGroups = resourceGroups;
        this.vmsses = vmsses;
        this.virtualMachines = virtualMachines;
        this.virtualNetworks = virtualNetworks;
        this.applicationGateways = applicationGateways;
        this.subnets = subnets;
        this.user = user;
    }
}
