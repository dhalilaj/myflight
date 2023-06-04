package lhind.internship.myflight.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter@Setter
public class CreateBookingRequest {
    private Long id;
    private Long[] flights;
}
