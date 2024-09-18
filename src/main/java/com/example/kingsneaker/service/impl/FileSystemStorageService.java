package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.exception.StorageException;
//import com.tunesmusic.properties.AlbumImageStorageProperties;
//import com.tunesmusic.properties.AudioStorageProperties;
import com.example.kingsneaker.properties.ImageStorageProperties;
import com.example.kingsneaker.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path imageRootLocation;

    private String getFileType(MultipartFile file) {
        // Implement a method to determine the file type based on the file extension
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (fileExtension.equals("jpg") || fileExtension.equals("png")) {
            return "image";
        } else if (fileExtension.equals("mp3")) {
           throw  new StorageException("mp3 type of file is not supported yet !");
        } else if (fileExtension.equals("mp4")) {
            throw  new StorageException("mp4 type of file is not supported yet !");
        } else {
            throw  new StorageException("this type of file is not supported yet !");
        }
    }

    @Autowired
    public FileSystemStorageService(ImageStorageProperties imageProperties) {
        if (imageProperties.getUploadDir().trim().isEmpty()) {
            throw new StorageException("Image upload location cannot be empty.");
        }
        this.imageRootLocation = Paths.get(imageProperties.getUploadDir());

    }


    @Override
    public void init() {

    }

    @Override
    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        // Generate a unique filename
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

        // Determine the destination path based on file type
        String fileType = getFileType(file);
        Path destinationFile;
        if (fileType.equals("image")) {
            destinationFile = imageRootLocation.resolve(uniqueFilename);
        }
//        else if (fileType.equals("audio")) {
//            destinationFile = audioRootLocation.resolve(uniqueFilename);
//        }
        else {
            throw new StorageException("This file type is not supported!");
        }

        // Save the file to the destination path
        try {
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + uniqueFilename, e);
        }

        // Return the unique filename for database storage
        return uniqueFilename;
    }

//    @Override
//    public String storeImageAlbum(MultipartFile file) {
//        if (file.isEmpty()){
//            throw new StorageException("file can not be empty !");
//        }
//
//        String originalFilename = file.getOriginalFilename();
//        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
//        Path destinationFile;
//        destinationFile = albumImageRootLocation.resolve(uniqueFilename);
//        try {
//            Files.copy(file.getInputStream(),destinationFile,StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e){
//            throw new StorageException("Failed to store file " + uniqueFilename, e);
//        }
//        return uniqueFilename;
//    }


    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
