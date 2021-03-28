package com.reactspringapp.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileDataAcessService userProfileDataAcessService;

    @Autowired
    public UserProfileService (UserProfileDataAcessService userProfileDataAcessService) {
        this.userProfileDataAcessService = userProfileDataAcessService;
    }
    List<UserProfile> getUserProfiles(){
        return userProfileDataAcessService.getUserProfiles();
    }
}
