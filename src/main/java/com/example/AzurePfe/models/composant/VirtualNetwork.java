package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import com.example.AzurePfe.models.ressources.Region;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "virtualNetwork")
public class VirtualNetwork {
    @Id
    private String id;
    private String name;

    private String ipAddresses;

    @DBRef
    private ResourceGroup resourceGroup;
    @DBRef
    private Region region;
    @DBRef
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VirtualNetwork(String id, String name, String ipAddresses, ResourceGroup resourceGroup, Region region, User user) {
        this.id = id;
        this.name = name;
        this.ipAddresses = ipAddresses;
        this.resourceGroup = resourceGroup;
        this.region = region;
        this.user = user;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(String ipAddresses) {
        this.ipAddresses = ipAddresses;
    }



    public ResourceGroup getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroup = resourceGroup;
    }
    public VirtualNetwork(String id) {
        this.id = id;
    }

    public VirtualNetwork(String id, String name, String ipAddresses, ResourceGroup resourceGroup, Region region) {
        this.id = id;
        this.name = name;
        this.ipAddresses = ipAddresses;

        this.resourceGroup = resourceGroup;
        this.region = region;
    }

    public VirtualNetwork() {
    }
}
