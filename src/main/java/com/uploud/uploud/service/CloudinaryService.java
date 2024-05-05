package com.uploud.uploud.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    Cloudinary cloudinary;
    public CloudinaryService () {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "djleslzyt");
        valuesMap.put("api_key", "683383817317257");
        valuesMap.put("api_secret", "YPb4XUTbqmFZIA6ftsOt7DCyEP4-9w");
        cloudinary = new Cloudinary(valuesMap);
    }
    public Map uploud (MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        return  result;
    }
    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
