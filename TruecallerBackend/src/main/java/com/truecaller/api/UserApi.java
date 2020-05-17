package com.truecaller.api;

import com.truecaller.common.BasicRdo;
import com.truecaller.config.JwtTokenUtil;
import com.truecaller.entity.User;
import com.truecaller.model.JwtRequest;
import com.truecaller.model.JwtResponse;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;
import com.truecaller.model.UserRdo;
import com.truecaller.service.JwtUserDetailsService;
import com.truecaller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserApi {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<BasicRdo> signUp(@RequestBody @Validated SignupQdo signupQdo) {
        BasicRdo<LoginRdo> basicRdo = new BasicRdo<>();
        //check if user with given phone number is already registered
        User userByPhone = userService.getUserByPhone(signupQdo.getPhone());
        if (userByPhone != null) {
            return basicRdo.getResponse("User already exist", HttpStatus.BAD_REQUEST, null);
        }
        // sign up
        LoginRdo loginRdo = userService.signUp(signupQdo);
        if (loginRdo != null) {
            basicRdo.getResponse("Registration successfull", HttpStatus.OK, loginRdo);
        }
        return basicRdo.getResponse("Registation failed", HttpStatus.BAD_REQUEST, null);

    }

    @GetMapping("/login")
    public ResponseEntity<BasicRdo> login(@RequestBody @Validated LoginQdo loginQdo) {
        BasicRdo<LoginRdo> basicRdo = new BasicRdo<>();
        LoginRdo loginRdo = userService.login(loginQdo);
        if (loginRdo != null) {
            return basicRdo.getResponse("Login success", HttpStatus.OK, loginRdo);
        }
        return basicRdo.getResponse("Login failed", HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping
    public ResponseEntity<BasicRdo> getUserDetails(@RequestParam(required = false, name = "search", defaultValue = "") String search) {
        BasicRdo<List<UserRdo>> basicRdo = new BasicRdo<>();
        List<UserRdo> userDetails = userService.getUserDetails(search);
        return basicRdo.getResponse("Details fetched successfully", HttpStatus.OK, userDetails);
    }
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
    
    private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
