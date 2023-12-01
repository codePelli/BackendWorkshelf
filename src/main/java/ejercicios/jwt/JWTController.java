package ejercicios.jwt;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ejercicios.exception.UserNotFoundException;


/**
 * @author Samson Effes
 */


@RestController
@RequestMapping("/login")
public class JWTController {
	@Autowired
    private JWTService jwtService;
	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public Object getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest authRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            String token =  jwtService.generateToken(authRequest.getUserName());
            JSONObject jsonObject = new JSONObject("{\"token\": \"" + token + "\"}");
            jsonObject.put("token",token );
            return jsonObject.toMap();//devuelve token por body
        }
        else {
            throw new UserNotFoundException("Invalid user credentials");
        }
    }
}