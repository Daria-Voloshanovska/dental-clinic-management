package backend.clinicmanagement.controller;

import backend.clinicmanagement.dto.AuthDto;
import backend.clinicmanagement.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthControllerTest {
    @Test
    void loginReturnsToken() {
        AuthService service = mock(AuthService.class);
        when(service.login(any())).thenReturn(new AuthDto.AuthResponse("token123"));
        AuthController controller = new AuthController(service);
        ResponseEntity<AuthDto.AuthResponse> resp = controller.login(new AuthDto.LoginRequest("a@a.com","pass"));
        assertEquals(200, resp.getStatusCode().value());
        assertEquals("token123", resp.getBody().token());
    }

}
