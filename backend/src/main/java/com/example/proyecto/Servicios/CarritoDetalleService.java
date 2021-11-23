package com.example.proyecto.Servicios;



import com.example.proyecto.Modelo.CarritoDetalle;
import com.example.proyecto.Repositorios.CarritoDRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoDetalleService{
    
    @Autowired
    private CarritoDRepo cRepo;

    public <S extends CarritoDetalle> S save(S entity) {
        return cRepo.save(entity);
    }
  
    public void deleteAll() {
       cRepo.deleteAll();
        
    }


}
