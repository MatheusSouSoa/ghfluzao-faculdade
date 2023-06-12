package br.com.api.ghfluzao.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.services.UsuarioTokensService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class AuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtServiceInterface _jwtService;
    @Autowired
    private UsuarioServiceInterface _usuarioService;
    @Autowired
    private UsuarioTokensService _tokensService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
        if(request.getServletPath().contains("/api-v3/auth") || request.getServletPath().contains("/api-v3/usuarios/criar")){
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getServletPath().contains("swagger") || request.getServletPath().contains("docs") || request.getServletPath().contains("https://ghfluzao-faculdade-production.up.railway.app/")){
            filterChain.doFilter(request, response);
            return;
        }

        var token = request.getHeader("Authorization");
        var userId = request.getHeader("RequestedBy");

        if(token == null || userId == null || !token.startsWith("Bearer ")) {
            response.getWriter().write("Usuario não autorizado!");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        boolean isValidToken = false;

        try {
            isValidToken = _jwtService.isValidToken(token.substring(7), userId);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            _tokensService.setarTokenFalse(Long.parseLong(userId), token);
            return;
        }

        if(isValidToken) {
            try {
                var usuario = _usuarioService.pegarUsuarioPorId(Long.parseLong(userId));

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                response.getWriter().write(e.getMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } else {
            response.getWriter().write("token invalido.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);

    }
    
}
