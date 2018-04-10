package com.foodorderingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodorderingapp.model.Food;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */
@Entity
@Table(name = "tbl_restaurant")
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", nullable = false, updatable = false)
    private int id;

    @Column(name = "restaurant_name")
    @NotNull(message = "please enter the restaurant name")
    @Size(min = 3, max = 25)
    private String name;

    @Column(name = "restaurant_address")
    @NotBlank(message = "please enter the restaurant address")
    @Size(min = 3, max = 25)
    private String address;

    @Column(name = "restaurant_contact")
    @NotBlank(message = "please enter the restaurant contact")
    @Size(min = 7, max = 10)
    private String contact;

    @Column(name = "status")
    private boolean isActive = true;

    @Column(name = "restaurant_code")
    private String restaurantCode;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public void setRestaurantCode(String restaurantCode) {
        this.restaurantCode = restaurantCode;
    }


    public Restaurant(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Restaurant() {
    this.restaurantCode = "REST" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
