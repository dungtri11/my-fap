package com.portal.fap.controller;

import com.portal.fap.entity.Image;
import com.portal.fap.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PatchMapping("/save")
    public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile file,
                                       @RequestParam(value = "userid", required = false) Long userid) throws IOException {
        Image response = imageService.saveImage(file, userid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> showImage(@PathVariable String name) throws IOException {
        Image image = imageService.getImageByName(name);

        String filePath = image.getPath();
        byte[] imageData = Files.readAllBytes(new File(filePath).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(image.getType()))
                .body(imageData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeImage(@PathVariable Long id) throws IOException {
        imageService.removeImage(id);
        return ResponseEntity.ok("Image has completely remove");
    }
}
