package com.reactspringapp.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin(origins = "*", allowedHeaders = "*") //do not use this (*) in production or deployment
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController (UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
                 path = "{userProfileId}/image/upload",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)

    public void uploadUserProfileImage(@PathVariable("userProfileId")UUID userProfileID,
                                       @RequestParam("file")MultipartFile file) {
        userProfileService.uploadUserProfileImage(userProfileID, file);
    }
}
