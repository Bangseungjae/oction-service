package com.octionservice.web;

import com.octionservice.entity.Type;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseFileDto {

    @ApiModelProperty(value = "파일의 경로")
    private String url;

    @ApiModelProperty(value = "aws s3에 등록된 파일 이름이다. 이 파일 이름으로 파일 삭제가 가능하다.")
    private String filename;

    @ApiModelProperty(value = "유저의 이름")
    private String username;

    @ApiModelProperty(value = "등록한 자산의 이름")
    private String nftName;

    @ApiModelProperty(value = "등록할 디지털 자산 타입 ex(Art, Video, Music, Picture)")
    private Type type;

    @ApiModelProperty(value = "유저가 등록할 떄의 파일의 이름")
    private String originalName;


    public static ResponseFileDto of(String url, String filename, String originalName){
        return ResponseFileDto.builder()
                .url(url)
                .filename(filename)
                .originalName(originalName)
                .build();
    }
}
