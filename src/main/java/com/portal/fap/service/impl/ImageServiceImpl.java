package com.portal.fap.service.impl;


import com.portal.fap.entity.Image;
import com.portal.fap.entity.User;
import com.portal.fap.exception.BadRequestResponseException;
import com.portal.fap.exception.NotFoundResponseException;
import com.portal.fap.repository.ImageRepository;
import com.portal.fap.service.ImageService;
import com.portal.fap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageServiceImpl implements ImageService {
    private final String IMAGE_REPO_PATH = "E:/JAVA/NewFAP/img/";
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserService userService;

    @Override
    public Image getImageByName(String filename) throws IOException {
        Image image = imageRepository.findByName(filename).orElseThrow(() ->
                new NotFoundResponseException("201 : Can't find image source"));
        return image;
    }

    @Override
    public Image saveImage(MultipartFile file, Long userid) throws IOException {
        User user = userService.findById(userid);
        Long imageId = (user.getImage() == null ? null : user.getImage().getId());
        long current = System.currentTimeMillis();
        String path = IMAGE_REPO_PATH + current + file.getOriginalFilename().replaceAll("\s", "_");
        if (imageId != null && imageRepository.existsById(imageId)) {
            removeImage(imageId);
        }
        Image image = imageRepository.save(
                (imageId == null ?  Image.builder()
                        .name(current + file.getOriginalFilename())
                        .type(file.getContentType())
                        .path(path).build() :
                        Image.builder()
                                .name(current + file.getOriginalFilename())
                                .type(file.getContentType())
                                .path(path)
                                .id(imageId).build())
        );
        user.setImage(image);
        userService.save(user);
        file.transferTo(new File(path));
        return image;
    }

    @Override
    public void removeImage(Long id) throws IOException {
        Image image = imageRepository.findById(id).orElseThrow(() ->
                new BadRequestResponseException("202 : Can't find suitable image data"));
        Files.delete(new File(image.getPath()).toPath());
    }
}
