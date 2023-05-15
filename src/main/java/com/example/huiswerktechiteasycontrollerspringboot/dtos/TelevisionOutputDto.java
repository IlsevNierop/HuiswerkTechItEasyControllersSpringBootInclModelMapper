package com.example.huiswerktechiteasycontrollerspringboot.dtos;

import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class TelevisionOutputDto {

    private Long id;
    private String type;
    private String brand;
    private String name;
    private double price;
    private double availableSize;
    private double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private int originalStock;
    private int sold;

    public void copyProperties(Television target){
        BeanUtils.copyProperties(this, target);
    }
}
