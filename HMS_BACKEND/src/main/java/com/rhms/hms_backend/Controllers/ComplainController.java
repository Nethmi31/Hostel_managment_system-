package com.rhms.hms_backend.Controllers;


import com.rhms.hms_backend.Models.Complain;
import com.rhms.hms_backend.Services.ComplainService;
import com.rhms.hms_backend.Util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/complaints")
public class ComplainController {

    @Autowired
    private ComplainService complainService;



    @PostMapping("/complainSave")
    public ResponseEntity<String> saveComplain(Complain complain, @RequestParam("image") MultipartFile multipartFile) {
        try {
            if (!multipartFile.isEmpty()) {
                String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                complain.setImageName(filename);
                Complain savedComplain = complainService.SaveComplain(complain);
                String uploadPath = "images/" + savedComplain.getId();

                FileUploadUtil.saveFile(uploadPath, filename, multipartFile);
            } else {
                if (complain.getImageFile().isEmpty()) {
                    complain.setImageFile(null);
                }
                complainService.SaveComplain(complain);
            }

            return ResponseEntity.ok("Complain saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while saving Complain: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }





}
