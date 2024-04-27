package com.perdidoseachados.perdidoseachados.Repository;

import com.perdidoseachados.perdidoseachados.Entidades.Estado;
import com.perdidoseachados.perdidoseachados.Entidades.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
}
