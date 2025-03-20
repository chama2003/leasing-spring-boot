package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.BankDetailDto;
import com.example.leasing_backend.dto.TestimonialDto;
import com.example.leasing_backend.service.BankDetailService;
import com.example.leasing_backend.service.TestimonialService;
import com.example.leasing_backend.service.impl.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/bank")
public class BankDetailController {

    private final BankDetailService bankDetailService;
    private final StorageService storageService;

    public BankDetailController(BankDetailService bankDetailService, StorageService storageService) {
        this.bankDetailService = bankDetailService;
        this.storageService = storageService;
    }

    // Create Bank Detail without file
    @PostMapping
    public ResponseEntity<BankDetailDto> createBankDetail(@RequestBody BankDetailDto bankDetailDto) {
        BankDetailDto savedBankDetail = bankDetailService.createBankDetail(bankDetailDto);
        return new ResponseEntity<>(savedBankDetail, HttpStatus.CREATED);
    }

    // Get Bank Detail by account number
    @GetMapping("{accountNo}")
    public ResponseEntity<BankDetailDto> getBankDetailById(@PathVariable("accountNo") String accountNo) {
        BankDetailDto bankDetailDto = bankDetailService.getBankDetailById(accountNo);
        return ResponseEntity.ok(bankDetailDto);
    }

    // Get all Bank Details
    @GetMapping
    public ResponseEntity<List<BankDetailDto>> getAllBankDetails() {
        List<BankDetailDto> bankDetails = bankDetailService.getAllBankDetails();
        return ResponseEntity.ok(bankDetails);
    }

    // Update Bank Detail by account number
    @PutMapping("{accountNo}")
    public ResponseEntity<BankDetailDto> updateBankDetailById(@PathVariable("accountNo") String accountNo,
                                                              @RequestBody BankDetailDto updatedBankDetail) {
        BankDetailDto bankDetailDto = bankDetailService.updateBankDetailById(accountNo, updatedBankDetail);
        return ResponseEntity.ok(bankDetailDto);
    }

    // Delete Bank Detail by account number
    @DeleteMapping("{accountNo}")
    public ResponseEntity<String> deleteBankDetailById(@PathVariable("accountNo") String accountNo) {
        bankDetailService.deleteBankDetailById(accountNo);
        return ResponseEntity.ok("Bank detail deleted successfully");
    }

    // Create Bank Detail with file upload
    @PostMapping("/add")
    public ResponseEntity<BankDetailDto> createBankDetailWithFile(
            @RequestParam("image") MultipartFile file,
            @RequestParam("AccountType") String accountType,
            @RequestParam("accountno") String accountno,
            @RequestParam("BankName") String bankName,
            @RequestParam("Branch") String branch) throws IOException {

        // Upload the image via StorageService
        String fileName = storageService.uploadImage(file);

        // Create BankDetailDto and set details
        BankDetailDto bankDetailDto = new BankDetailDto();
        bankDetailDto.setAccountType(accountType);
        bankDetailDto.setBankName(bankName);
        bankDetailDto.setAccountNo(accountno);
        bankDetailDto.setBranch(branch);
        bankDetailDto.setBankbookFirstpage(fileName); // Store the image filename in the database

        // Save the BankDetailDto and return the response
        BankDetailDto savedBankDetail = bankDetailService.createBankDetail(bankDetailDto);
        return new ResponseEntity<>(savedBankDetail, HttpStatus.CREATED);
    }

    // Download Image (Bankbook First Page)
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.ok().body(imageData);
    }
}
