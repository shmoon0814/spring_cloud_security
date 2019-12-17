package m.s.h.cloudserver.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleModel {

    @ApiModelProperty(value = "값")
    private Integer value;

    @ApiModelProperty(value = "이름")
    private String name;


}