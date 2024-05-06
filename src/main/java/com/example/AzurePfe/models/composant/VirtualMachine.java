package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import com.example.AzurePfe.models.ressources.DiskSize;
import com.example.AzurePfe.models.ressources.Region;
import com.example.AzurePfe.models.ressources.VMImage;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "virtualMachine")
public class VirtualMachine {
    @Id
    private String id;
    private String name;
    @DBRef
    private Region region;
    @DBRef
    private ResourceGroup resourceGroupe;

    @DBRef
    private VMImage idImage;
    @DBRef
    private Subnet subnet;

    private String Username;
    private String Password;
    @DBRef
    private DiskSize idDiskSize;

    @DBRef
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VirtualMachine(String id, String name, Region region, ResourceGroup resourceGroupe , Subnet subnet , VMImage idImage, String username, String password, DiskSize idDiskSize, User user) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.resourceGroupe = resourceGroupe;
        this.subnet = subnet;
        this.idImage = idImage;
        Username = username;
        Password = password;
        this.idDiskSize = idDiskSize;
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

    public ResourceGroup getResourceGroupe() {
        return resourceGroupe;
    }

    public void setResourceGroupe(ResourceGroup resourceGroupe) {
        this.resourceGroupe = resourceGroupe;
    }

    public Subnet getSubnet() {
        return subnet;
    }

    public void setSubnet(Subnet subnet) {
        this.subnet = subnet;
    }

    public VMImage getIdImage() {
        return idImage;
    }

    public void setIdImage(VMImage idImage) {
        this.idImage = idImage;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public DiskSize getIdDiskSize() {
        return idDiskSize;
    }

    public void setIdDiskSize(DiskSize idDiskSize) {
        this.idDiskSize = idDiskSize;
    }

    public VirtualMachine(String id) {
        this.id = id;
    }

    public VirtualMachine(String id, String name, Region region, ResourceGroup resourceGroupe, Subnet subnet , VMImage idImage, String username, String password, DiskSize idDiskSize) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.resourceGroupe = resourceGroupe;
        this.subnet = subnet;
        this.idImage = idImage;
        Username = username;
        Password = password;
        this.idDiskSize = idDiskSize;
    }

    public VirtualMachine() {
    }
}
