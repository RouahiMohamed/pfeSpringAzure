package com.example.AzurePfe.models.composant;

import com.example.AzurePfe.models.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vmss")
public class Vmss {
    @Id
    private String id;
    private String name;
    @DBRef
    private VirtualMachine virtualMachine;
    private Integer nb_vm;

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
    @DBRef
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vmss(String id, String name, VirtualMachine virtualMachine, Integer nb_vm, User user) {
        this.id = id;
        this.name = name;
        this.virtualMachine = virtualMachine;
        this.nb_vm = nb_vm;
        this.user = user;
    }

    public Vmss() {
    }

    public Vmss(String id) {
        this.id = id;
    }

    public Vmss(String id, String name, VirtualMachine virtualMachine, Integer nb_vm) {
        this.id = id;
        this.name = name;
        this.virtualMachine = virtualMachine;
        this.nb_vm = nb_vm;
    }

    public Integer getNb_vm() {
        return nb_vm;
    }

    public void setNb_vm(Integer nb_vm) {
        this.nb_vm = nb_vm;
    }

    public VirtualMachine getVirtualMachine() {
        return virtualMachine;
    }

    public void setVirtualMachine(VirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
    }
}
