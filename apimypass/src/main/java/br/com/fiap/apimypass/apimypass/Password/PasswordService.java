package br.com.fiap.apimypass.apimypass.Password;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.apimypass.apimypass.Password.dto.PasswordDTO;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public List<PasswordDTO> getAllPasswords() {
        return passwordRepository.findAll().stream()
                .map(p -> new PasswordDTO(p.getUrl(), p.getUsername(), p.getPassword()))
                .collect(Collectors.toList());
    }

    public Password savePassword(PasswordDTO passwordDTO) {
        Password password = new Password(passwordDTO.getUrl(), passwordDTO.getUsername(), passwordDTO.getPassword());
        return passwordRepository.save(password);
    }
}