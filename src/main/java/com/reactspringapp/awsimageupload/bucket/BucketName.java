package com.reactspringapp.awsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}