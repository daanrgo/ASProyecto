package com.tvp.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tvp.compras.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> { }
