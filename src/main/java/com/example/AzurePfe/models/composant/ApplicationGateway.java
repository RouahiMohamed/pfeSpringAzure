package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import com.example.AzurePfe.models.ressources.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applicationGateway")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationGateway {
    @Id
    private String id;
    private String name;
    @DBRef
    private Region region;
    @DBRef
    private ResourceGroup resourceGroupe;
    @DBRef
    private Subnet subnet;
    private boolean autoscaling;
    private Integer minimum_Instance_Count;
    private Integer maximum_Instance_Count;
    @DBRef
    private User user;

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

    public boolean isAutoscaling() {
        return autoscaling;
    }

    public void setAutoscaling(boolean autoscaling) {
        this.autoscaling = autoscaling;
    }

    public Integer getMinimum_Instance_Count() {
        return minimum_Instance_Count;
    }

    public void setMinimum_Instance_Count(Integer minimum_Instance_Count) {
        this.minimum_Instance_Count = minimum_Instance_Count;
    }

    public Integer getMaximum_Instance_Count() {
        return maximum_Instance_Count;
    }

    public void setMaximum_Instance_Count(Integer maximum_Instance_Count) {
        this.maximum_Instance_Count = maximum_Instance_Count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
