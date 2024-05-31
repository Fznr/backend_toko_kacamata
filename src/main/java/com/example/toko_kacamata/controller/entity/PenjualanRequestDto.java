
package com.example.toko_kacamata.controller.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenjualanRequestDto {
    private PenjualanDto penjualanDto;
    private List<PenjualanItemDto> penjualanItemDtos;
}
