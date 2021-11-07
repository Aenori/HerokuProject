package wcsdata.xmen.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import wcsdata.xmen.entity.Post;
import wcsdata.xmen.repository.CerebookUserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SampleControllerTest {
    @InjectMocks
    SampleController sampleController = new SampleController();

    @Mock
    private CerebookUserRepository mockCerebookUserRepository;

    @Test
    void getAllCerebookUsers() {
        when(mockCerebookUserRepository.findAll()).thenReturn(new ArrayList<>());
        assert(sampleController.getAllCerebookUser().isEmpty());
    }
}