package org.example.bucketservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemDto {
    private int id;

    @Size(min = 1, max = 100)
    @NotNull
    @NotEmpty
    private String title;

    @Size(min = 1, max = 2000)
    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private double price;

    @NotNull
    private String provider;

    public ItemDto() {}

    public ItemDto(String title, String description, double price, String provider) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
