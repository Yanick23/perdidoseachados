package com.perdidoseachados.perdidoseachados.Servicies;

import com.perdidoseachados.perdidoseachados.DTOs.UsuarioDTO;
import com.perdidoseachados.perdidoseachados.Entidades.Role;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Repository.RoleRepository;
import com.perdidoseachados.perdidoseachados.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService  {

}
