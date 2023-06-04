package lhind.internship.myflight.exception;

public class UserIdNotFoundExceptoin extends RuntimeException{
    public UserIdNotFoundExceptoin(Long id) {
        super("User with id : " + id + " does not exist");
    }
}
