package com.perdidoseachados.perdidoseachados.Servicies;


import com.perdidoseachados.perdidoseachados.DTOs.UsuarioDTO;
import  com.perdidoseachados.perdidoseachados.DTOs.GenerateResponse;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioInsertDTO;
import com.perdidoseachados.perdidoseachados.Entidades.PasswordResetToken;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Entidades.Role;
import com.perdidoseachados.perdidoseachados.Repository.PasswordResetTokenRepository;
import com.perdidoseachados.perdidoseachados.Repository.RoleRepository;
import com.perdidoseachados.perdidoseachados.Repository.UsuarioRepository;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.ResourceNotFoundException;
import com.perdidoseachados.perdidoseachados.config.SecurityConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private EmailService emailService;


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

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

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
        Role role = new Role();
        role.setId(usuarioDTO.getRoleDTO().getId());

        mapDTOToUser(entity,usuarioDTO);
        entity.setPassword(usuarioDTO.getPassword());
        entity.setRole(role);
        entity.setEstadoDaConta(true);
        entity.setPassword(securityConfiguration.passwordEncoder().encode(usuarioDTO.getPassword()));


        return new UsuarioDTO(usuarioRepository.save(entity));
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

    public GenerateResponse resetPassword(HttpServletRequest request,  String userEmail) {


           Usuario usuario = usuarioRepository.findByEmail(userEmail)
                   .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));

           String token = UUID.randomUUID().toString();
         //  createPasswordResetTokenForUser(usuario, token);

           emailService.sendResetTokenEmail(request.getContextPath(),request.getLocale().toString(),token,userEmail);
           return new GenerateResponse("Solicitação de redefinição de senha iniciada com sucesso.");



    }

    public void createPasswordResetTokenForUser(Usuario user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(myToken);
    }


    public String authenticateUser(UsuarioInsertDTO usuarioInsertDTO) {

        Usuario usuario = usuarioRepository.findByEmail(usuarioInsertDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        if (!usuario.isAccountNonLocked()) {
            return "Usuário está bloqueado, entre em contacto com o helpdesk";
        }

        // Procede com a autenticação
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioInsertDTO.getEmail(), usuarioInsertDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Usuario userDetails = (Usuario) authentication.getPrincipal();

        String token = jwtTokenService.generateToken(userDetails);
        return token;
    }

    public  void mapDTOToUser(Usuario usuario, UsuarioDTO usuarioDTO){

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setFoto(usuarioDTO.getFoto());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setPrimeiro_nome(usuarioDTO.getPrimeiroNome());
        usuario.setSegundo_nome(usuarioDTO.getSegundoNome());
        usuario.setEstadoDaConta(usuarioDTO.isEstado_da_conta());
        usuario.setId(usuarioDTO.getId());

        if (usuarioDTO.getRoleDTO() != null){
            Role role = roleRepository.findById(usuarioDTO.getRoleDTO().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Role "+usuarioDTO.getRoleDTO().getId()+" nao encontrado ( UsuarioService -> mapDTOToUser() )  ) "));
            usuario.setRole(role);
        }


    }


    @Transactional
    public void blockUser(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario nao encontrado (UsuarioService -> blockUser() )"));
        usuario.setEstadoDaConta(false);
        usuarioRepository.save(usuario);
    }


    @Transactional
    public void unblockUser(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario nao encontrado (UsuarioService -> unblockUser() )"));
        usuario.setEstadoDaConta(true);
        usuarioRepository.save(usuario);
    }





}
