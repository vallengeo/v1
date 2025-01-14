package com.vallengeo.global.service;

import com.vallengeo.cidadao.service.S3Service;
import com.vallengeo.core.util.FileUnzipper;
import com.vallengeo.global.model.Arquivo;
import com.vallengeo.global.repository.ArquivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vallengeo.core.config.Config.APPLICATION_TEMP_UPLOAD;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArquivoService {
    private final ArquivoRepository repository;
    private final S3Service s3Service;

    public List<String> filesUnzipped(MultipartFile file) throws IOException {
        log.info("Descompactando arquivo: {}", file.getOriginalFilename());

        final var extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        final var tempFileId = UUID.randomUUID();
        final var tempFileName = tempFileId + extension;
        final var filePath = APPLICATION_TEMP_UPLOAD + tempFileName;
        final var unzipFilesPath = APPLICATION_TEMP_UPLOAD + tempFileId;
        final var dest = new File(filePath);
        file.transferTo(dest);

        return FileUnzipper.unzip(filePath, unzipFilesPath);
    }

     public Optional<Arquivo> findById(UUID idArquivo) {
        return repository.findById(idArquivo);
    }

    public ByteArrayResource getResource(Arquivo arquivo) {
        byte[] bytesFile = s3Service.downloadFile(arquivo.getId().toString(),  arquivo.getExtensao());
        return new ByteArrayResource(bytesFile);
    }

    public String getContentType(Arquivo arquivo) {
        return s3Service.getContentTypeFromS3(arquivo.getId().toString(),  arquivo.getExtensao());
    }
}
