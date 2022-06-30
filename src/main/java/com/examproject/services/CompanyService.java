package com.examproject.services;

import com.examproject.exceptions.NotFoundException1;
import com.examproject.models.Company;
import com.examproject.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new NotFoundException1(
                "Company with id: " + id + " not found!"
        ));
    }

    public void updateCompany(Company newCompany, Long id) {
        newCompany.setId(id);
        companyRepository.save(newCompany);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

}
