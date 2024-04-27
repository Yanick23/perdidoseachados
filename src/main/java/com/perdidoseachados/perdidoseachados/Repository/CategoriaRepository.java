package com.perdidoseachados.perdidoseachados.Repository;

import com.perdidoseachados.perdidoseachados.Entidades.Categoria;
import com.perdidoseachados.perdidoseachados.Entidades.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
