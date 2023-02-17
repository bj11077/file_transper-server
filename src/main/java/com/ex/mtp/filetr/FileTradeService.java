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

}

