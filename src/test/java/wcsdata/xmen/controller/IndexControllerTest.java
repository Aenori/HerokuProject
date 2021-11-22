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
class IndexControllerTest {
    @InjectMocks
    private IndexController indexController;

    @Mock
    private CerebookUserRepository mockCerebookUserRepository;
}