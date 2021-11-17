package wcsdata.xmen.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wcsdata.xmen.repository.CerebookUserRepository;

import java.util.ArrayList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SampleControllerTest {
    @InjectMocks
    private SampleController sampleController;

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