package com.tvp.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tvp.pagos.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> { }
