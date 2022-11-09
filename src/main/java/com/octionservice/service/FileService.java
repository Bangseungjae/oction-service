package com.octionservice.service;

import com.octionservice.entity.FileEntity;
import com.octionservice.entity.repository.FileRepository;
import com.octionservice.web.ResponseFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(ResponseFileDto fileDto) {
        FileEntity fileEntity = FileEntity.builder()
                .urls(fileDto.getUrl())
                .filename(fileDto.getFilename())
                .username(fileDto.getUsername())
                .nftName(fileDto.getNftName())
                .originalName(fileDto.getOriginalName())
                .build();
        fileRepository.save(fileEntity);
    }

    public List<ResponseFileDto> files() {
        List<FileEntity> all = fileRepository.findAll();
        List<ResponseFileDto> list = new ArrayList<>();
        for (FileEntity fileEntity : all) {
            ResponseFileDto fileDto = new ResponseFileDto(
                    fileEntity.getUrls(),
                    fileEntity.getFilename(),
                    fileEntity.getUsername(),
                    fileEntity.getNftName(),
                    fileEntity.getType(),
                    fileEntity.getOriginalName()
            );

            list.add(fileDto);
        }
        return list;
    }

    public List<ResponseFileDto> find10RecentResponseFileDto() {
        List<FileEntity> top10ByOrderByIdDesc = fileRepository.findTop10ByOrderByIdDesc();
        List<ResponseFileDto> fileDtos = new ArrayList<>();
        for (FileEntity f : top10ByOrderByIdDesc) {
            ResponseFileDto responseFileDto = new ResponseFileDto(
                    f.getUrls(),
                    f.getFilename(),
                    f.getUsername(),
                    f.getNftName(),
                    f.getType(),
                    f.getOriginalName()
            );
            fileDtos.add(responseFileDto);
        }
        return fileDtos;
    }

}
