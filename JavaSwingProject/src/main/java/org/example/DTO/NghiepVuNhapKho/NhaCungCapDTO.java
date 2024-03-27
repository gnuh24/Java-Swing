package org.example.DTO.NghiepVuNhapKho;

import lombok.*;

import java.util.List;


@Data
// @Data = @Setter + @Getter + @RequiredArgsConstructor + .....
public class NhaCungCapDTO {

    @NonNull
    private Integer maNCC;

    @NonNull
    private String tenNCC;

    @NonNull
    private String email;

    @NonNull
    private String soDienThoai;

    public NhaCungCapDTO(){}


}
