package com.gabriel.devgram.services;

import com.gabriel.devgram.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;




    public String store(MultipartFile file, String subfolder ) {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extension;

        Path destinationFolder = Paths.get(uploadDir, subfolder);

        Path destinationPath = destinationFolder.resolve(newFileName);

        try {
            if(!Files.exists(destinationFolder)) {
                Files.createDirectories(destinationFolder);
            }
            Files.copy(file.getInputStream(), destinationPath);
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo", e);
        }

        return newFileName;
    }

    public Resource load(String filename, String subfolder) {
        Path filePath = Paths.get(uploadDir, subfolder, filename);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new ObjectNotFoundException("Arquivo não encontrado: " + filename);
            }
        } catch (Exception e) {
            throw new ObjectNotFoundException("Arquivo não encontrado: " + filename);
        }

    }




}
