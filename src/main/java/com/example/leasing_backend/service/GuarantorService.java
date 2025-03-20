package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.GuarantorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GuarantorService {

    GuarantorDto createGuarantor(GuarantorDto guarantorDto);

    GuarantorDto getGuarantorById(Long guarantorNic);

    List<GuarantorDto> getAllGuarantors();

    GuarantorDto updateGuarantorById(Long guarantorNic, GuarantorDto updatedGuarantor);

    void deleteGuarantorById(Long guarantorNic);
}
