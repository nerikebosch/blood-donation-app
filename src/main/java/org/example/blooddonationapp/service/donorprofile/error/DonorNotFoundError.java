package org.example.blooddonationapp.service.donorprofile.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DonorNotFoundError extends RuntimeException{
    public DonorNotFoundError(long id) {
        super("Donor with " + id + " was not found");
    }


}
