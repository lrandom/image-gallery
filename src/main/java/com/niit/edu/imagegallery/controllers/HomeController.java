package com.niit.edu.imagegallery.controllers;

import com.niit.edu.imagegallery.models.Image;
import com.niit.edu.imagegallery.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class HomeController {
    @Value("${UPLOAD_FOLDER}")
    public String UPLOAD_FOLDER;

    @Autowired
    ImageService imageService;

    @GetMapping("/")
   public String index(Model model){
        ArrayList<Image> images =  this.imageService.getAll();
        model.addAttribute("images",images);
        return "index";
   }

    @PostMapping("/do-upload-file")
    public String doUpload(@RequestParam(name = "image") MultipartFile file){
        try {
            //buoc 1: convert file thanh mang bytes
            byte[] bytesFile = file.getBytes();

            //1_2021, 2_2021, 3_2021
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            String newDirName = month+"_"+year;
            File newDir = new File(UPLOAD_FOLDER+newDirName);///Users/mac/Documents/spring-uploads/1_2021
            if(!newDir.exists() || newDir.isFile()){
                //tạo mới thư mục
                newDir.mkdir();
            }

            String pathOfFile= newDirName+"/"+System.currentTimeMillis()+file.getOriginalFilename();
            //buoc 2: tao đối tuợng path
            Path path = Paths.get(UPLOAD_FOLDER+pathOfFile);

            //buoc 3: ghi file vào trong thư mục
            Files.write(path, bytesFile);


            //buoc 4: lưu lại đường dẫn file vào trong db
            this.imageService.saveFileName(pathOfFile);
            //Hoan thanh
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "redirect:/";
    }

    @GetMapping("/delete-image")
    public String deleteImage(@RequestParam Long id){
        Image image = this.imageService.getImage(id);
        //xoá ảnh
        File file = new File(UPLOAD_FOLDER+"/"+image.getPath());
        if(file.exists()){
            file.delete();
        }

        this.imageService.delete(id);
        return "redirect:/";
    }
}
