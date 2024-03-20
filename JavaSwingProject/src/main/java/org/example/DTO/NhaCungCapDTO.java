package org.example.DTO;

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
    private List<String> stringList;

    public NhaCungCapDTO(){}


}
