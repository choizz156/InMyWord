package inmyword.server.config.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import inmyword.server.user.dto.LoginResponseDto;
import inmyword.server.user.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Slf4j
@RequiredArgsConstructor
public class UserAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        setResponse(response, getResDto());
    }

    private void setResponse(HttpServletResponse response, String dto) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(dto);
    }

    private String getResDto() throws JsonProcessingException {
        User user = User.loginUser();
        LoginResponseDto responseDto = LoginResponseDto.builder()
            .displayName(user.getDisplayName())
            .email(user.getEmail())
            .createAt(user.getCreateAt())
            .build();

        return objectMapper.writeValueAsString(responseDto);
    }


}
