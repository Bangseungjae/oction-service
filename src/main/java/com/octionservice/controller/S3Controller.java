package com.octionservice.controller;

import com.octionservice.service.FileService;
import com.octionservice.service.S3Service;
import com.octionservice.web.RequestFileDto;
import com.octionservice.web.ResponseFileDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = "파일 API를 제공")
public class S3Controller {

    private final S3Service s3Service;
    private final FileService fileService;

    @ApiOperation(value = "파일을 등록한다")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "file등록", required = true, dataType = "image, video", paramType = "query"),
            @ApiImplicitParam(name = "requestFileDto", value = "{\"username\":\"test\", \"nftName\": \"nftTest\", \"type\": \"Music\", \"price\":1000}", required = true, dataType = "json", paramType = "query")
    })
    @PostMapping(value = "/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart("requestFileDto") RequestFileDto requestFileDto,
                             @RequestPart("file") MultipartFile file
                             ) throws IOException {
        log.info("getOriginalFilename: {}", file.getOriginalFilename());
        log.info("getName: {}", file.getName());
        ResponseFileDto fileDto = s3Service.uploadFile(file);

        ResponseFileDto responseFileDto = new ResponseFileDto(
                fileDto.getUrl(),
                fileDto.getFilename(),
                requestFileDto.getUsername(),
                requestFileDto.getNftName(),
                requestFileDto.getType(),
                file.getOriginalFilename(),
                0,
                requestFileDto.getPrice(),
                requestFileDto.getDescription()
        );

        fileService.save(responseFileDto);

        return fileDto.getUrl();
    }

    @ApiOperation(value = "파일을 삭제한다")
    @DeleteMapping("/api/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@ApiParam(value = "s3의 파일 이름", example = "5adb8274-b0c7-4f6f-a49f-7c89b257948f") @RequestParam("key") String key) {
        s3Service.deleteFile(key);
    }

    @ApiOperation(value = "모든 파일 제공")
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFileDto>> files() {
        List<ResponseFileDto> files = fileService.files();
        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/")
    public String healthy() {
        return "healthy~~~9";
    }

    @ApiOperation(value = "최신 10개의 파일 제공")
    @GetMapping("/10files")
    public ResponseEntity<List<ResponseFileDto>> getLast10Files() {
        List<ResponseFileDto> recentResponseFileDto = fileService.find10RecentResponseFileDto();
        return ResponseEntity.ok().body(recentResponseFileDto);
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseFileDto> getDetailByUrl(@RequestParam("url") String url) {
        ResponseFileDto byUrls = fileService.findByUrls(url);
        return ResponseEntity.ok().body(byUrls);
    }
}
