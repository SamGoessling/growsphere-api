package org.launchcode.growsphere.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
          throws IOException, ServletException {
    logger.error("Unauthorized error: {}", authException.getMessage());

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    final Map<String, Object> body = new HashMap<>();
    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    body.put("error", "Unauthorized");

    // Adding a more descriptive message based on the type of AuthenticationException
    if (authException.getCause() != null) {
      body.put("message", diagnoseIssue(authException));
    } else {
      body.put("message", "Authorization header is missing or the provided token is not valid.");
    }

    body.put("path", request.getServletPath());

    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), body);
  }

  // Helper method to diagnose the cause of the AuthenticationException
  private String diagnoseIssue(AuthenticationException authException) {
    String message = authException.getMessage();

    // Add more conditions based on the types of exceptions that your application needs to handle.
    if (message.contains("expired")) {
      return "The token has expired. Please login again to obtain a new token.";
    } else if (message.contains("invalid")) {
      return "The token is invalid. Check if the token was correctly set and has not been tampered with.";
    } else if (message.contains("unsupported")) {
      return "The token is of an unsupported type.";
    } else if (message.contains("empty")) {
      return "The token is missing. Please include an authorization token with your request.";
    } else {
      // Default to generic message if the specific error type cannot be ascertained
      return "Unauthorized access. Please check the authorization token or contact support.";
    }
  }
}
