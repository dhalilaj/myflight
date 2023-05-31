package lhind.internship.myflight.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse extends BaseResponse {

    private String token;

}
