package lhind.internship.myflight.services;

import lhind.internship.myflight.model.dto.AuthenticationRequest;
import lhind.internship.myflight.model.dto.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
