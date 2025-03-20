package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.GuarantorDto;
import com.example.leasing_backend.entity.Guarantor;

public class GuarantorMapper {

    public static GuarantorDto mapToGuarantorDto(Guarantor guarantor) {
        return new GuarantorDto(
                guarantor.getGuarantorNic(),
                guarantor.getGuarantorNicImg(),
                guarantor.getGuarantorsName(),
                guarantor.getGuarantorOccupation(),
                guarantor.getGuarantorIncome()
        );
    }

    public static Guarantor mapToGuarantor(GuarantorDto guarantorDto) {
        Guarantor guarantor = new Guarantor();
        guarantor.setGuarantorNic(guarantorDto.getGuarantorNic());
        guarantor.setGuarantorNicImg(guarantorDto.getGuarantorNicImg());
        guarantor.setGuarantorsName(guarantorDto.getGuarantorsName());
        guarantor.setGuarantorOccupation(guarantorDto.getGuarantorOccupation());
        guarantor.setGuarantorIncome(guarantorDto.getGuarantorIncome());
        return guarantor;
    }
}
