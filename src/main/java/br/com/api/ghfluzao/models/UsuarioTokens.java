package br.com.api.ghfluzao.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tokens")
@NoArgsConstructor
public class UsuarioTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo_usuario;
    private String token;
    private Date createdAt;
    private boolean isValid;

    public UsuarioTokens(Long codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
        this.createdAt = new Date();
        this.isValid = true;
    }

    
}
