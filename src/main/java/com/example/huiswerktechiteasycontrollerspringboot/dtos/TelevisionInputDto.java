package com.example.huiswerktechiteasycontrollerspringboot.dtos;

import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelevisionInputDto {


    private String type;
    @NotBlank(message = "Brand can't be blank")
    private String brand;
    @Size(max=20,message = "Name cannot have more characters than 20.")
    private String name;

    @PositiveOrZero(message="Price needs to be zero or higher, can't be negative")
    private double price;
    @PositiveOrZero(message="Size needs to be zero or higher, can't be negative")
    private double availableSize;
    private double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;

    @PositiveOrZero(message="Stock needs to be zero or higher, can't be negative")
    private int originalStock;
    @PositiveOrZero(message="Sold needs to be zero or higher, can't be negative")
    private int sold;



}
