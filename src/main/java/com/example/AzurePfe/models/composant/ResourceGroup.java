package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import com.example.AzurePfe.models.ressources.Region;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document(collection = "resourceGroups")
public class ResourceGroup {
    @Id
    private String id;
    private String name;

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

    public ResourceGroup(String id) {
        this.id = id;
    }

    public ResourceGroup(String id, String name, Region region, User user) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.user = user;
    }

    public ResourceGroup(String id, String name, Region region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public ResourceGroup() {
    }
}
