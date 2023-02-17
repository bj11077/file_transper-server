package com.ex.mtp.filetr;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileTradeService {

    @Value("${window.filePath}")
    private String path;

    public void fileUploadResponse(MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
    }

    public TradeDto fileDownloadResponse() throws IOException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        int read;
        int size = inputStream.available();

        byte[] buf = new byte[size];
        try {
            while((read = inputStream.read(buf)) != -1){
                os.write(buf,0,read);
            }
        }finally {
            os.close();
        }
//        System.out.println("byteArray");
//        System.out.println(buf.toString());
//        for (byte b : buf) {
//            System.out.println(b);
//        }
        TradeDto dto = new TradeDto(file.getName(),buf);
        return dto;
    }

    public TradeDto fileDownloadResponseToZip(String path) throws IOException {
        // directory path
        File dir = new File(path);
        TradeDto dto = new TradeDto();
        File[] files;
        if(!dir.exists()){
            return null;
        }else if((files = dir.listFiles()).length == 0 ){
            return null;
        }
        String zipName = "complete.zip";
        String filePath = path+File.separator+zipName;
        FileOutputStream outputStream = new FileOutputStream(filePath);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        FileInputStream inputStream = null;
        for (File file : files) {
            inputStream = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while((length = inputStream.read(bytes)) >= 0){
                zipOutputStream.write(bytes,0,length);
            }
            inputStream.close();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        outputStream.close();
        dto.setFileName(zipName);
        dto.setFile(fileToByteArray(filePath));
        File delFile = new File(filePath);
        delFile.delete();
        return dto;
    }

    public byte[] fileToByteArray(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }

}

