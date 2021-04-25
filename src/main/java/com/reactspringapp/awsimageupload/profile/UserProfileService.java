package com.reactspringapp.awsimageupload.profile;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file){
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() +"]");
        }

        if (!Arrays.asList(ContentType.IMAGE_JPEG, ContentType.IMAGE_PNG, ContentType.IMAGE_GIF).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image!");
        }
    }
}
