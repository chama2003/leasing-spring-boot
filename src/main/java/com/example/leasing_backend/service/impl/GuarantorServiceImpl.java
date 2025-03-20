package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.GuarantorDto;
import com.example.leasing_backend.entity.Guarantor;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.mapper.GuarantorMapper;
import com.example.leasing_backend.repository.GuarantorRepository;
import com.example.leasing_backend.service.GuarantorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GuarantorServiceImpl implements GuarantorService {

    private final GuarantorRepository guarantorRepository;

    @Override
    @Transactional
    public GuarantorDto createGuarantor(GuarantorDto guarantorDto) {
        Guarantor guarantor = GuarantorMapper.mapToGuarantor(guarantorDto);
        Guarantor savedGuarantor = guarantorRepository.save(guarantor);
        return GuarantorMapper.mapToGuarantorDto(savedGuarantor);
    }

    @Override
    @Transactional
    public GuarantorDto getGuarantorById(Long guarantorNic) {
        Guarantor guarantor = guarantorRepository.findById(guarantorNic)
                .orElseThrow(() -> new ResourceNotFoundException("Guarantor not found with NIC: " + guarantorNic));
        return GuarantorMapper.mapToGuarantorDto(guarantor);
    }

    @Override
    @Transactional
    public List<GuarantorDto> getAllGuarantors() {
        List<Guarantor> guarantors = guarantorRepository.findAll();
        return guarantors.stream()
                .map(GuarantorMapper::mapToGuarantorDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GuarantorDto updateGuarantorById(Long guarantorNic, GuarantorDto updatedGuarantor) {
        Guarantor guarantor = guarantorRepository.findById(guarantorNic)
                .orElseThrow(() -> new ResourceNotFoundException("Guarantor not found with NIC: " + guarantorNic));

        guarantor.setGuarantorNicImg(updatedGuarantor.getGuarantorNicImg());
        guarantor.setGuarantorsName(updatedGuarantor.getGuarantorsName());
        guarantor.setGuarantorOccupation(updatedGuarantor.getGuarantorOccupation());
        guarantor.setGuarantorIncome(updatedGuarantor.getGuarantorIncome());

        Guarantor savedGuarantor = guarantorRepository.save(guarantor);
        return GuarantorMapper.mapToGuarantorDto(savedGuarantor);
    }

    @Override
    @Transactional
    public void deleteGuarantorById(Long guarantorNic) {
        Guarantor guarantor = guarantorRepository.findById(guarantorNic)
                .orElseThrow(() -> new ResourceNotFoundException("Guarantor not found with NIC: " + guarantorNic));
        guarantorRepository.deleteById(guarantorNic);
    }
}
