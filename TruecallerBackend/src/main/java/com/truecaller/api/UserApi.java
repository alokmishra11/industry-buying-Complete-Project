package com.truecaller.api;

import com.truecaller.common.BasicRdo;
import com.truecaller.model.UserRdo;
import com.truecaller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<BasicRdo> getUserDetails(){
        BasicRdo<List<UserRdo>> basicRdo = new BasicRdo<>();
        List<UserRdo> userDetails = userService.getUserDetails();
        return basicRdo.getResponse("Details fetched successfully", HttpStatus.OK, userDetails);
    }
}
