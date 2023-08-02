package com.example.druidtest.conrtroller;

import com.example.druidtest.model.FileMessage;
import com.example.druidtest.util.IdWorkerUtil;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("file")
public class FileController {

    List<String> limitFilePartArrays = Lists.newArrayList("theme", "drawings", "externalLinks");


    @GetMapping("fileSize")
    public int test(MultipartFile file) throws Exception {

        String originFileName = file.getOriginalFilename();
        String uploadName = generateNginxExcelName(originFileName);

        Map<String, List<FileMessage>> fileMessageMap = this.checkFileContent(file, uploadName);

        List<FileMessage> themeList = fileMessageMap.get("themeList");
        List<FileMessage> sheetList = fileMessageMap.get("sheetList");

        themeList.forEach(f -> {
            System.out.println(f.getFileSize());
        });

        return 1;
    }

    private String generateNginxExcelName(String fileName) throws Exception {
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if (!".xlsx".equals(fileType) && !".xls".equals(fileType)) {
            throw new Exception(fileName);
        }

        return IdWorkerUtil.getId() + fileType;
    }

    private Map<String, List<FileMessage>> checkFileContent(MultipartFile file, String uploadName) {
        String suffix = uploadName.substring(uploadName.lastIndexOf("."));

        Map<String, List<FileMessage>> map = new HashMap<>();

        List<FileMessage> themeList = new LinkedList<>();
        List<FileMessage> sheetList = new LinkedList<>();

        map.put("themeList", themeList);
        map.put("sheetList", sheetList);

        File tempFile = null;
        try {
            tempFile = File.createTempFile("excel", suffix);

            file.transferTo(tempFile);
            Path zipPath = Paths.get(tempFile.getPath());
            FileSystem fileSystems = FileSystems.newFileSystem(zipPath, null);

            // zip文件下根目录 如要指定目录可配置指定的目录 如：/nginx-1.12.2/conf
            Path startPath = fileSystems.getPath("/");

            // 遍历zip包
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    if (file.getParent().getFileName() != null) {
                        if (limitFilePartArrays.contains(String.valueOf(file.getParent().getFileName()))) {
                            themeList.add(new FileMessage(file.toString(), attrs.size()));
                        } else if ("worksheets".equalsIgnoreCase(String.valueOf(file.getParent().getFileName()))) {
                            sheetList.add(new FileMessage(file.toString(), attrs.size()));
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }

            });

            return map;

        } catch (IOException | ProviderNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        return map;
    }


}
