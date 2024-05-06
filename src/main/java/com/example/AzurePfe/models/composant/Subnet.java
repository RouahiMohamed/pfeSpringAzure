package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "subnet")
public class Subnet {
    @Id
    private String id;
    private String name;
    private String adress;
    @DBRef
    private Set<VirtualNetwork> virtualNetworks = new HashSet<>();
    @DBRef
    private User user;

    public User getUser() {
        return user;
    }
    public Set<VirtualNetwork> getVirtualNetworks() {
        return virtualNetworks;
    }

    public void setVirtualNetworks(Set<VirtualNetwork> virtualNetworks) {
        this.virtualNetworks = virtualNetworks;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }
    public Subnet(String id) {
        this.id = id;
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

    public String getAdress() {
        return adress;
    }

    public Subnet(String id, String name, String adress, Set<VirtualNetwork> virtualNetworks) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.virtualNetworks = virtualNetworks;
    }

    public Subnet(String id, String name, String adress, User user, Set<VirtualNetwork> virtualNetworks) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.user = user;
        this.virtualNetworks = virtualNetworks;
    }

    public Subnet() {
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
