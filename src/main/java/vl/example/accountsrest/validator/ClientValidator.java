package vl.example.accountsrest.validator;

import vl.example.accountsrest.dto.ClientDTO;
import vl.example.accountsrest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vl.example.accountsrest.exception.CustomBadRequestException;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientValidator implements Validator {

    private final ClientService clientService;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(ClientDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ClientDTO clientDTO = (ClientDTO) target;

//        if (clientService.checkByEmail(clientDTO.getEmail(), clientDTO.getId()))
//            errors.rejectValue("email", "", "Client with same Email already present");
//
//        if (clientService.checkByName(clientDTO.getName(), clientDTO.getId()))
//            errors.rejectValue("name", "", "Client with same Name already present");

        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors().stream()
                    .map(e -> String.join(" - ", e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.joining("\n"));

            throw new CustomBadRequestException(errorMessage);
        }
    }
}