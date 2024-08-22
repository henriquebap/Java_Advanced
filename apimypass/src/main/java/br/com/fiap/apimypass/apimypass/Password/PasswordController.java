package br.com.fiap.apimypass.apimypass.Password;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.apimypass.apimypass.Password.dto.PasswordDTO;

@RestController
@RequestMapping("/api")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/pass")
    public List<PasswordDTO> getAllPasswords(@RequestHeader("Authorization") String token) {
        // Validação do token aqui
        return passwordService.getAllPasswords();
    }
}