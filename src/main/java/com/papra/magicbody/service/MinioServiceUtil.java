package com.papra.magicbody.service;

import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class MinioServiceUtil {



    private void createMinioBucket(MinioClient minioClient, String minioBucketName) throws IOException,
        InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, ErrorResponseException,
        NoResponseException, InvalidBucketNameException, XmlPullParserException, InternalException,
        RegionConflictException, InvalidObjectPrefixException {
        minioClient.makeBucket(minioBucketName);
        String bucketPublicPolicy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::" + minioBucketName + "\"],\"Sid\":\"\"},{\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::" + minioBucketName + "/*\"],\"Sid\":\"\"}]}";
        minioClient.setBucketPolicy(minioBucketName, bucketPublicPolicy);
    }

    public String uploadFileToMinio(byte[] file) {
        String filename = UUID.randomUUID().toString();
        try{
        MinioClient minioClient = new MinioClient("https://minio.cloud.papraco.com/", "arsham", "@salam123");

        int counter = 0;
        String tmpPath = "/tmp/" + filename;
        FileUtils.writeByteArrayToFile(new File(tmpPath), file);
        minioClient.putObject("test", filename, tmpPath);
        }catch(Exception e){
            e.printStackTrace();
        }
        return filename;
    }

    public String getLink(String filename){
        try {
            MinioClient minioClient = new MinioClient("https://minio.cloud.papraco.com/", "arsham", "@salam123");
            return minioClient.getPresignedObjectUrl(Method.GET, "test", filename, 60, null);
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
    }

}
