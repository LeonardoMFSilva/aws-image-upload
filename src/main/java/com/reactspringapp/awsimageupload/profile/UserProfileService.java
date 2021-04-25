package com.reactspringapp.awsimageupload.profile;

import com.reactspringapp.awsimageupload.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UserProfileService {
    private final UserProfileDataAcessService userProfileDataAcessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAcessService userProfileDataAcessService, FileStore fileStore) {
        this.userProfileDataAcessService = userProfileDataAcessService;
        this.fileStore = fileStore;
    }
    List<UserProfile> getUserProfiles(){
        return userProfileDataAcessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file){
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() +"]");
        } // Checking if the image is not empty

        if (!Arrays.asList(ContentType.IMAGE_JPEG, ContentType.IMAGE_PNG, ContentType.IMAGE_GIF).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image!");
        } // Checking if the file is an image

        UserProfile user = userProfileDataAcessService.
                getUserProfiles()
                .stream() //search for stream in Java
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId)) //what this does until here: goes through a list, then filters a userProfile which the id is equal to the one that came from the client
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found.", userProfileId)));

        Map<String, String>  metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));
        // grabbing metadata from file

        fileStore.save();
    }

}
