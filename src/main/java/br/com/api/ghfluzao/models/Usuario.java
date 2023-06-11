package br.com.api.ghfluzao.models;

import java.util.List;

import br.com.api.ghfluzao.enums.RolesUsuarios;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private RolesUsuarios role;
    @OneToMany
    @JoinColumn(name = "codigo_usuario")
    private List<UsuarioTokens> tokens;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.role = RolesUsuarios.USER;
    }

    public void setRole(RolesUsuarios role) {
        this.role = role;
    }

}
