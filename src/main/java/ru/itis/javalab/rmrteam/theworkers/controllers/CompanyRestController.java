package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.dto.CompanyInfoDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.CompanyInfoService;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class CompanyRestController {
    @Autowired
    private CompanyInfoService companyInfoService;

    @PostMapping(value = "/companyProfile")
    public ResponseEntity<?> changeProfile(@RequestBody CompanyInfoDto companyInfoDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        if (userDetails.getRole().equals(Role.COMPANY) && userDetails.getId().equals(companyInfoDto.getId())) {
            companyInfoService.updateCompanyInfo(companyInfoDto, userDetails.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/companyProfile/{id}")
    public ResponseEntity<CompanyInfoDto> getCompany(@PathVariable(name = "id") Long id) {
        CompanyInfoDto companyInfoDto;
        if (companyInfoService.getCompanyInfo(id).isPresent()) {
            companyInfoDto = companyInfoService.getCompanyInfo(id).get();
            return new ResponseEntity<>(companyInfoDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
