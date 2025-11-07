package com.tvp.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tvp.compras.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> { }
