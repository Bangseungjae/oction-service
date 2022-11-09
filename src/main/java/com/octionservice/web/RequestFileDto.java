package com.octionservice.web;

import com.octionservice.entity.Type;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestFileDto {
    @ApiModelProperty(value = "사용자의 이름 혹은 닉네임")
    private String username;

    @ApiModelProperty(value = "등록할 디지털 자산 이름")
    private String nftName;

    @ApiModelProperty(value = "등록할 디지털 자산 타입 ex(Art, Video, Music, Picture)")
    private Type type;
}
