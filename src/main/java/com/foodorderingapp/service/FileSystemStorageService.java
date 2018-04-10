package com.foodorderingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final Path frontEndLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.frontEndLocation = Paths.get(properties.getFElocation());
    }

    @Override
    public void store(MultipartFile file, String newFileName) {
        String filename = newFileName;
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            Files.copy(file.getInputStream(), this.frontEndLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("==================================");
            System.out.println("File stored successfully with filename " + filename + " !");
            System.out.println("==================================");
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }
}
