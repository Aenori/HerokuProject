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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SampleControllerTest {
    @InjectMocks
    SampleController sampleController;

    @Mock
    private CerebookUserRepository mockCerebookUserRepository;

    @Test
    void getAllCerebookUsers() {
        when(mockCerebookUserRepository.findAll()).thenReturn(new ArrayList<>());
        assert(sampleController.getAllCerebookUser().isEmpty());
        verify(mockCerebookUserRepository, times(1)).findAll();
        verify(mockCerebookUserRepository, never()).findById(any());
    }
}