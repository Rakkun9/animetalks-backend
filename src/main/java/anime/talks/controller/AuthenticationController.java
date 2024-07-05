package anime.talks.controller;

import anime.talks.domain.user.DataAuthenticationUser;
import anime.talks.domain.user.User;
import anime.talks.infra.security.DataJWTToken;
import anime.talks.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
    private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
    public ResponseEntity AuthenticateUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser){
      Authentication authToken = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.name(), dataAuthenticationUser.password());
      var userAuthenticated = authenticationManager.authenticate(authToken);
      var JWTToken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
        // Code to authenticate user
        return ResponseEntity.ok(new DataJWTToken(JWTToken));
    }
}
