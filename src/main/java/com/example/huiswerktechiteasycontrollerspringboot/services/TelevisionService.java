package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.TelevisionInputDto;
import com.example.huiswerktechiteasycontrollerspringboot.dtos.TelevisionOutputDto;
import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.TelevisionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    ModelMapper modelMapper = new ModelMapper();



    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionOutputDto> getAllTelevisions() throws RecordNotFoundException{
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAll();
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database");
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = modelMapper.map(t, TelevisionOutputDto.class);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }

    public List<TelevisionOutputDto> getAllTelevisionsOrderedByBrand(){
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAllByOrderByBrand();
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database");
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = modelMapper.map(t, TelevisionOutputDto.class);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }
    public List<TelevisionOutputDto> getAllTelevisionsOrderedByPrice(){
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAllByOrderByPrice();
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database");
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = modelMapper.map(t, TelevisionOutputDto.class);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }

    public TelevisionOutputDto getTelevisionById (Long id) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
//        TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(television);
        TelevisionOutputDto televisionOutputDto = modelMapper.map(television, TelevisionOutputDto.class);
        return televisionOutputDto;

    }
    public List<TelevisionOutputDto> getTelevisionByBrand (String brand) {
        ArrayList<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findByBrandIgnoreCase(brand);
        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("There are currently no televisions in the database with brand " + brand);
        }
        for (Television t : televisions) {
            TelevisionOutputDto televisionOutputDto = modelMapper.map(t, TelevisionOutputDto.class);
            televisionOutputDtos.add(televisionOutputDto);
        }
        return televisionOutputDtos;
    }


    public TelevisionOutputDto createTelevision(TelevisionInputDto televisionInputDto) {
        Television television = modelMapper.map(televisionInputDto, Television.class);
        televisionRepository.save((television));
        TelevisionOutputDto televisionOutputDto = modelMapper.map(television, TelevisionOutputDto.class);
        return televisionOutputDto;
    }

    public TelevisionOutputDto updateTelevision(Long id, TelevisionInputDto televisionInputDto)  throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
        // als de variabelen niet ingevuld worden in client side, dan worden deze variabelen gevuld met de volgende waardes: 0, 0.0, null, of false (=default boolean) (afhankelijk van type variabele). Daarom eerst checken of de variabelen wel meegegeven worden in de body.
        //Bij de inputDto wordt momenteel een aantal variabelen gecheckt op notBank etc, maar sommige niet. Die kunnen voor null vervangen worden zonder onderstaande checks.
        if (televisionInputDto.getType() != null) {
            television.setType(televisionInputDto.getType());
        }
        if (televisionInputDto.getBrand() != null) {
            television.setBrand(televisionInputDto.getBrand());
        }
        if (televisionInputDto.getName() != null) {
            television.setName(televisionInputDto.getName());
        }
        if (televisionInputDto.getPrice() != 0.0) {
            television.setPrice(televisionInputDto.getPrice());
        }
        if (televisionInputDto.getAvailableSize() != 0.0) {
            television.setAvailableSize(televisionInputDto.getAvailableSize());
        }
        if (televisionInputDto.getRefreshRate() != 0.0) {
            television.setRefreshRate(televisionInputDto.getRefreshRate());
        }
        if (televisionInputDto.getScreenType() != null) {
            television.setScreenType(televisionInputDto.getScreenType());
        }
        if (televisionInputDto.getScreenQuality() != null) {
            television.setScreenQuality(televisionInputDto.getScreenQuality());
        }
        // Door in de klasse niet boolean met kleine letter (=primitieve variabele die alleen true of false kan teruggeven), maar Boolean met hoofdletter (die kan ook null zijn) te gebruiken, kan je checken of die in de body zit.
        if (televisionInputDto.getSmartTv() != null) {
            television.setSmartTv(televisionInputDto.getSmartTv());
        }
        if (televisionInputDto.getVoiceControl() != null) {
            television.setVoiceControl(televisionInputDto.getVoiceControl());
        }
        if (televisionInputDto.getHdr() != null) {
            television.setHdr(televisionInputDto.getHdr());
        }
        if (televisionInputDto.getBluetooth() != null) {
            television.setBluetooth(televisionInputDto.getBluetooth());
        }
        if (televisionInputDto.getAmbiLight() != null) {
            television.setAmbiLight(televisionInputDto.getAmbiLight());
        }
        if (televisionInputDto.getOriginalStock() != 0) {
            television.setOriginalStock(televisionInputDto.getOriginalStock());
        }
        if (televisionInputDto.getSold() != 0) {
            television.setSold(televisionInputDto.getSold());
        }
        televisionRepository.save(television);

        return modelMapper.map(television, TelevisionOutputDto.class);
    }

    public String deleteTelevision (Long id) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));

        televisionRepository.delete(television);

        return "Television successfully removed";
    }





//    public Television transferInputDtoToTelevisionModel(TelevisionInputDto televisionInputDto){
//        Television television = new Television();
//        television.setType(televisionInputDto.type);
//        television.setBrand(televisionInputDto.brand);
//        television.setName(televisionInputDto.name);
//        television.setPrice(televisionInputDto.price);
//        television.setAvailableSize(televisionInputDto.availableSize);
//        television.setRefreshRate(televisionInputDto.refreshRate);
//        television.setScreenType(televisionInputDto.screenType);
//        television.setScreenQuality(televisionInputDto.screenQuality);
//        television.setSmartTv(televisionInputDto.smartTv);
//        television.setVoiceControl(televisionInputDto.voiceControl);
//        television.setHdr(televisionInputDto.hdr);
//        television.setBluetooth(televisionInputDto.bluetooth);
//        television.setAmbiLight(televisionInputDto.ambiLight);
//        television.setOriginalStock(televisionInputDto.originalStock);
//        television.setSold(televisionInputDto.sold);
//
//        return television;
//    }
//    public TelevisionOutputDto transferTelevisionModelToOutputDto(Television television){
//        TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();
////        televisionOutputDto.id = television.getId();
////        televisionOutputDto.type = television.getType();
////        televisionOutputDto.brand = television.getBrand();
////        televisionOutputDto.name = television.getName();
////        televisionOutputDto.price = television.getPrice();
////        televisionOutputDto.availableSize = television.getAvailableSize();
////        televisionOutputDto.refreshRate = television.getRefreshRate();
////        televisionOutputDto.screenType = television.getScreenType();
////        televisionOutputDto.screenQuality = television.getScreenQuality();
////        televisionOutputDto.smartTv = television.getSmartTv();
////        televisionOutputDto.voiceControl = television.getVoiceControl();
////        televisionOutputDto.hdr = television.getHdr();
////        televisionOutputDto.bluetooth = television.getBluetooth();
////        televisionOutputDto.ambiLight = television.getAmbiLight();
////        televisionOutputDto.originalStock = television.getOriginalStock();
////        televisionOutputDto.sold = television.getSold();
//
//        return televisionOutputDto;
//
//    }


}
