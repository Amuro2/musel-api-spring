package com.amtak.muselspringapi.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class SongFileService {
    private final static String LOCATION = "files/songs";

    private final Path rootLocation;

    public SongFileService() {
        rootLocation = Paths.get(LOCATION);
    }

    public void store(MultipartFile multipartFile, int id) {
        String filename = String.valueOf(id);

        try {
            if(multipartFile == null || multipartFile.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();

            if(!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory.");
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                if(!Files.isDirectory(rootLocation)) {
                    Files.deleteIfExists(rootLocation);
                    // TODO create directories recursively if they do not exist
                    Files.createDirectory(rootLocation);
                }

                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch(IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    public Resource loadAsResource(int id) {
        String filename = String.valueOf(id);

        try {
            Path file = rootLocation.resolve(filename);

            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return resource;
            }

            else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch(MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    public byte[] loadAsByteArray(int id) throws IOException {
        String filename = String.valueOf(id);

        try {
            Path file = rootLocation.resolve(filename);

            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return resource.getContentAsByteArray();
            }

            else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch(MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
}
