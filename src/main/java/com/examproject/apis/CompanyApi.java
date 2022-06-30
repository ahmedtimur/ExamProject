package com.examproject.apis;

import com.examproject.dto.CompanyDTO;
import com.examproject.models.Company;
import com.examproject.models.Course;
import com.examproject.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyApi {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final EntityManagerFactory entityManagerFactory;

    @GetMapping
    public List<CompanyDTO> findAll() {
        return companyService.findAll().stream().map(this::convertToCompanyDTO).toList();
    }

    @GetMapping("/find/{id}")
    public CompanyDTO findById(@PathVariable Long id) {
        return convertToCompanyDTO(companyService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> createCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.saveCompany(convertToCompany(companyDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        companyService.updateCompany(convertToCompany(companyDTO), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/findCourses/{id}")
    public List<Course> findCompanysCourses(@PathVariable Long id, Company company){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select c from Course c join c.company a where a.id=?1", Course.class)
                .setParameter(1, id).getResultList();
    }

    private Company convertToCompany(CompanyDTO companyDTO) {  // map dto to entity
        return modelMapper.map(companyDTO, Company.class);
    }

    private CompanyDTO convertToCompanyDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

}
