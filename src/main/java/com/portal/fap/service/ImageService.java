package com.portal.fap.service;

import com.portal.fap.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public Image getImageByName(String filename) throws IOException;
    public Image saveImage(MultipartFile file, Long imageId) throws IOException;
    public void removeImage(Long id) throws IOException;

}
