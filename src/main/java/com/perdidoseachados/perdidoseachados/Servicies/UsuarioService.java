package com.perdidoseachados.perdidoseachados.Servicies;


import com.perdidoseachados.perdidoseachados.DTOs.UsuarioDTO;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioDTO;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioDTO;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioInsertDTO;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Entidades.Role;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Repository.RoleRepository;
import com.perdidoseachados.perdidoseachados.Repository.UsuarioRepository;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.DataBaseExeption;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.ResourceNotFoundException;
import com.perdidoseachados.perdidoseachados.config.SecurityConfiguration;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class UsuarioService implements UserDetailsService {

   @Autowired
   AuthService authService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

  @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    private SecurityConfiguration securityConfiguration;


    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public List<UsuarioDTO> findAll(){
        List<Usuario> itens = usuarioRepository.findAll();
        return itens.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO findById(Long id){

        authService.valideSelfOrAdmin(id);
        Optional<Usuario> optional = usuarioRepository.findById(id);
        Usuario entity = optional.orElseThrow(() -> new ResourceNotFoundException("Usuario "+id +" noo encontrado"));
        return new UsuarioDTO(entity) ;
    }




    @Transactional
    public UsuarioDTO insert( UsuarioInsertDTO usuarioDTO) {
        Usuario entity = new Usuario();

        mapDTOToUser(entity,usuarioDTO);
        entity.setPassword(securityConfiguration.passwordEncoder().encode(usuarioDTO.getPassword()));
        entity = usuarioRepository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public UsuarioDTO update (Long id, UsuarioDTO UsuarioDTO){

        Usuario entity = usuarioRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Usuario nao encontrado ( UsuarioService -> update() )"));
        mapDTOToUser(entity,UsuarioDTO);
        entity = usuarioRepository.save(entity);
        return new UsuarioDTO(entity);

    }

    @Transactional
    public String delete(Long id) {
        String reposta;
        try {
            usuarioRepository.deleteById(id);
            reposta = "Deletado";
            return reposta;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException ee) {
            throw new DataAccessException(ee.getMessage()
            ) {
                @Override
                public Throwable getRootCause() {
                    return super.getRootCause();
                }
            };
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(username).orElseThrow();
        return user;
    }

    // Método responsável por autenticar um usuário e retornar um token JWT
    public String authenticateUser(UsuarioInsertDTO usuarioInsertDTO) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
           new UsernamePasswordAuthenticationToken(usuarioInsertDTO.getEmail(), usuarioInsertDTO.getPassword()
                );
        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        Usuario userDetails = (Usuario) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);
        // Gera um token JWT para o usuário autenticado
          return token;
    }
    public  void mapDTOToUser(Usuario usuario, UsuarioDTO usuarioDTO){

        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setFoto(usuarioDTO.getFoto());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setPrimeiro_nome(usuarioDTO.getPrimeiroNome());
        usuario.setSegundo_nome(usuarioDTO.getSegundoNome());
        usuario.setEstadoDaConta(usuarioDTO.isEstado_da_conta());

        if (usuarioDTO.getRoleDTO() != null){
            Role role = roleRepository.findById(usuarioDTO.getRoleDTO().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Role "+usuarioDTO.getRoleDTO().getId()+" nao encontrado ( UsuarioService -> mapDTOToUser() )  ) "));
            usuario.setRole(role);
        }


    }





}
