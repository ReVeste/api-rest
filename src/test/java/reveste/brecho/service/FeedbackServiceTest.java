package reveste.brecho.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reveste.brecho.repository.FeedbackRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("6. Classes de teste | FeedbackService")
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @Nested
    @DisplayName("6.1 - listar")
    class FeedbackService1 {



    }

    @Nested
    @DisplayName("6.2 - buscarPorId")
    class FeedbackService2 {



    }

    @Nested
    @DisplayName("6.3 - criar")
    class FeedbackService3 {



    }

    @Nested
    @DisplayName("6.4 - atualizar")
    class FeedbackService4 {



    }

    @Nested
    @DisplayName("6.5 - deletar")
    class FeedbackService5 {



    }

}