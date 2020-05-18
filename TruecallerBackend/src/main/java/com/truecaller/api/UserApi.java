package com.truecaller.api;

import com.truecaller.common.BasicRdo;
import com.truecaller.entity.Contact;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;
import com.truecaller.service.ContactService;
import com.truecaller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    @PostMapping("/sign-up")
    public ResponseEntity<BasicRdo> signUp(@RequestBody @Validated SignupQdo signupQdo) throws Exception {
        BasicRdo<LoginRdo> basicRdo = new BasicRdo<>();
        //check if user with given phone number is already registered
        Contact contact = contactService.getOneByPhone(signupQdo.getPhone());
        if (contact == null) {
            return basicRdo.getResponse("Invalid contact number", HttpStatus.BAD_REQUEST, null);
        }
        if (contact.getUser() != null) {
            return basicRdo.getResponse("User already exist", HttpStatus.BAD_REQUEST, null);
        }
        // sign up
        LoginRdo loginRdo = userService.signUp(signupQdo, contact);
        if (loginRdo != null) {
            return basicRdo.getResponse("Success", HttpStatus.OK, loginRdo);
        }
        return basicRdo.getResponse("Registration failed", HttpStatus.BAD_REQUEST, null);

    }

    @GetMapping("/login")
    public ResponseEntity<BasicRdo> login(@RequestBody @Validated LoginQdo loginQdo) throws Exception {
        BasicRdo<LoginRdo> basicRdo = new BasicRdo<>();
        LoginRdo loginRdo = userService.login(loginQdo);
        if (loginRdo != null) {
            return basicRdo.getResponse("Success", HttpStatus.OK, loginRdo);
        }
        return basicRdo.getResponse("Login failed", HttpStatus.BAD_REQUEST, null);
    }

}
