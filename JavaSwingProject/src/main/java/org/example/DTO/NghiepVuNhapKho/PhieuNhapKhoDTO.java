package org.example.DTO.NghiepVuNhapKho;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class PhieuNhapKhoDTO {

    @NonNull
    private Integer maPhieu;

    @NonNull
    private LocalDateTime ngayNhapKho;

    @NonNull
    private Integer tongGiaTri;

    public PhieuNhapKhoDTO(){}

}
