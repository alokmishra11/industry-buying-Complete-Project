package com.truecaller.api;

import com.truecaller.common.BasicRdo;
import com.truecaller.model.UserRdo;
import com.truecaller.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactApi {

    @Autowired
    private ContactService contactService;

    @PutMapping("/mark-as-spam")
    public ResponseEntity<BasicRdo> markAsSpam(@RequestParam("phone") String phone,
                                               @RequestHeader("Authorization") String authToken) {
        BasicRdo<List<UserRdo>> basicRdo = new BasicRdo<>();
        boolean isMarked = contactService.markAsSpam(phone);
        if (isMarked) {
            return basicRdo.getResponse("Success", HttpStatus.OK, null, true);
        }
        return basicRdo.getResponse("Failed", HttpStatus.BAD_REQUEST, null, false);
    }
}
