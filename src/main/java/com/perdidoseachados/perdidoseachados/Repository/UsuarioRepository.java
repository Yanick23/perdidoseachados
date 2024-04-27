package com.perdidoseachados.perdidoseachados.Repository;

import com.perdidoseachados.perdidoseachados.Entidades.Localizacao;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


}
