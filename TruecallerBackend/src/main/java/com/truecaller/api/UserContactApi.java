package com.truecaller.api;

import com.truecaller.common.BasicRdo;
import com.truecaller.model.UserRdo;
import com.truecaller.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-details")
public class UserContactApi {

    @Autowired
    private UserContactService userContactService;

    @GetMapping
    public ResponseEntity<BasicRdo> getUserDetails(@RequestParam(required = false, name = "search", defaultValue = "") String search,
                                                   @RequestHeader("Authorization") String authToken) {
        BasicRdo<List<UserRdo>> basicRdo = new BasicRdo<>();
        List<UserRdo> userDetails = userContactService.getUserDetails(null, search);
        return basicRdo.getResponse("Success", HttpStatus.OK, userDetails);
    }
}
