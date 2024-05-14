package org.launchcode.growsphere.controllers;

import org.launchcode.growsphere.data.FileRepository;
import org.launchcode.growsphere.models.FileEntity;
import org.launchcode.growsphere.security.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileEntity fileEntity = fileStorageService.saveFile(file);
            fileRepository.save(fileEntity);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Could not upload file: " + e.getMessage());
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileEntity>> listAllFiles() {
        List<FileEntity> files = fileRepository.findAll();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) {
        try {
            FileEntity fileEntity = fileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("File not found with id " + id));
            Resource file = fileStorageService.loadAsResource(fileEntity.getFilename());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        try {
            FileEntity file = fileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("File not found with id " + id));
            fileStorageService.deleteFile(file.getFilename());
            fileRepository.delete(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not delete the file: " + e.getMessage());
        }
    }
}
