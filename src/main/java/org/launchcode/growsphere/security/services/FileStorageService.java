package org.launchcode.growsphere.security.services;

import org.launchcode.growsphere.models.FileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
    private static final List<String> allowedFileExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileEntity saveFile(MultipartFile file) {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if (!isExtensionAllowed(filename)) {
                throw new RuntimeException("Invalid file extension for file " + filename);
            }

            Path copyLocation = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File uploaded successfully: " + filename);

            FileEntity fileEntity = new FileEntity();
            fileEntity.setFilename(filename);
            fileEntity.setFilepath(copyLocation.toString());
            return fileEntity;

        } catch (Exception e) {
            logger.error("Could not store file " + file.getOriginalFilename() + ". Please try again!", e);
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!", e);
        }
    }

    private boolean isExtensionAllowed(String filename) {
        String extension = StringUtils.getFilenameExtension(filename);
        return allowedFileExtensions.contains(extension);
    }

    public void deleteFile(String filename) {
        try {
            Path filePath = Paths.get(uploadDir, filename);
            Files.deleteIfExists(filePath);
            logger.info("File deleted successfully: " + filename);
        } catch (Exception e) {
            logger.error("Failed to delete file " + filename, e);
            throw new RuntimeException("Failed to delete file " + filename, e);
        }
    }

    public Resource loadAsResource(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (Exception e) {
            logger.error("Could not load file as resource: " + filename, e);
            throw new RuntimeException("Could not load file as resource: " + filename, e);
        }
    }
}
