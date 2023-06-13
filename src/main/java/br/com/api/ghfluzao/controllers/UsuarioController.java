package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.enums.RolesUsuarios;
import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-v3/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceInterface usuarioServiceInterface;

    @Autowired
    private JwtServiceInterface _jwtService;

    @PostMapping("/criar")
    private ResponseEntity<?> criarUsuario(@RequestBody CreateUsuarioRequest request) {
        
        return usuarioServiceInterface.criarUsuario(request);
    }

    @GetMapping("/buscar")
    private ResponseEntity<?> pegarUsuariosPorEmail(@RequestParam("email") String email) {
        if(_jwtService.verificarRole(_jwtService.getUserId(), RolesUsuarios.ADMIN)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não tem permissão de acesso a essa rota");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceInterface.buscarUsuario(email));
    }

    @GetMapping("/buscar_historico")
    private ResponseEntity<?> pegarUsuarioshistoricoLoginPorEmail(@RequestParam("email") String email) {
        if(_jwtService.verificarRole(_jwtService.getUserId(), RolesUsuarios.ADMIN)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não tem permissão de acesso a essa rota");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceInterface.pegarUsuarioPorEmail(email));
    }

    @PutMapping("/setar-admin")
    public ResponseEntity<?> setarAdmin(@RequestParam("email") String email) {
        if(_jwtService.verificarRole(_jwtService.getUserId(), RolesUsuarios.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario não tem permissão de acesso a essa rota");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceInterface.definirRoleAdmin(email));
    }

    @PutMapping("/setar-func_inep")
    public ResponseEntity<?> setarFuncInep(@RequestParam("email") String email){
        if(_jwtService.verificarRole(_jwtService.getUserId(), RolesUsuarios.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario não tem permissão de acesso a essa rota");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceInterface.definirRoleFuncInep(email));
    }

    @PutMapping("/setar-professor")
    public ResponseEntity<?> setarProfessor(@RequestParam("email") String email, @RequestParam("codigo_assunto") Long codigo){
        if(_jwtService.verificarRole(_jwtService.getUserId(), RolesUsuarios.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario não tem permissão de acesso a essa rota");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceInterface.definirRoleProfessor(email, codigo));
    }

    @PutMapping("/setar-usuario")
    public ResponseEntity<?> setarUsuario(@RequestParam("email") String email){
        if(_jwtService.verificarRole(_jwtService.getUserId(), RolesUsuarios.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario não tem permissão de acesso a essa rota");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServiceInterface.definirRoleUser(email));
    }



}
