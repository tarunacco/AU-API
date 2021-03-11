package com.accolite.au.controllers;

import com.accolite.au.controller.BusinessUnitController;
import com.accolite.au.dto.BusinessUnitDTO;
import com.accolite.au.repositories.BusinessUnitRepository;
import com.accolite.au.services.BusinessUnitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BusinessUnitControllerTest{
    @InjectMocks
    private BusinessUnitService businessUnitService;

    private BusinessUnitRepository businessUnitRepository;
    private BusinessUnitDTO businessUnitDTO;
    private BusinessUnitController businessUnitController;


//    @BeforeEach
//    void setUp() {
//
//        this.businessUnitDTO = Mockito.mock(BusinessUnitDTO.class);
//        this.businessUnitController = new BusinessUnitController(businessUnitService);
//    }

    @Test
    public void addBusinessUnitSuccess() {
        BusinessUnitDTO businessUnitDTO = new BusinessUnitDTO("", "", "");
        ResponseEntity<List<BusinessUnitDTO>> allBUS = businessUnitController.getAllBusinessUnits();


    }


}
