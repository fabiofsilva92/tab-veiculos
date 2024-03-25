package com.itau.desafio.framework.adapter.out.persistence;

import com.itau.desafio.domain.db.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.marca = :marca AND v.ano = :ano")
    List<Veiculo> buscarByMarcaAndAno(String marca, Integer ano);

    List<Veiculo> findByAno(Integer ano);

    List<Veiculo> findByMarca(String marca);

    List<Veiculo> findByAnoBetween(Integer ano1, Integer ano2);

}
