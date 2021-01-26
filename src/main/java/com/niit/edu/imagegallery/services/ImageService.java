package com.niit.edu.imagegallery.services;

import com.niit.edu.imagegallery.models.Image;
import com.niit.edu.imagegallery.respositories.ImageJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ImageService {
    @Autowired
    ImageJpa imageJpa;

    public void saveFileName(String path) {
        Image image = new Image();
        image.setPath(path);
        this.imageJpa.save(image);
    }

    public ArrayList getAll() {
        Iterable it = this.imageJpa.findAll();
        ArrayList images = new ArrayList<Image>();
        for (Object img :
                it) {
            Image convertImage = (Image) img;
            convertImage.setPath("/uploads/" + convertImage.getPath());
            images.add(convertImage);
        }
        return images;
    }

    public Image getImage(Long id) {
        return this.imageJpa.findById(id).get();
    }

    public void delete(Long id){
         this.imageJpa.deleteById(id);
    }
}
