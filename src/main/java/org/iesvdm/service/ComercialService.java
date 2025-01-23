package org.iesvdm.service;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {

    private ComercialDAO comercialDAO;

    @Autowired
    PedidoDAO pedidoDAO;
    // Se utiliza inyección automática por constructor del framework Spring.
    // Por tanto, se puede omitir la anotación Autowired
    // @Autowired
    public ComercialService(ComercialDAO comercialDAO) {
        this.comercialDAO = comercialDAO;
    }


    public List<Comercial> listAll() {
        return comercialDAO.getAll();
    }

    public List<Pedido> listAllPedidos(int id) {
        return pedidoDAO.getAllByComercialId(id);
    }

    public List<PedidoDTO> listAllPedidosDTOByComercialId(int id){
        return pedidoDAO.getAllDTOByComercialId(id);
    }

    public Comercial one(Integer id) {
        Optional<Comercial> optCom = comercialDAO.find(id);
        return optCom.orElse(null);
    }

    public void newComercial(Comercial comercial) {
        comercialDAO.create(comercial);
    }

    public void replaceComercial(Comercial comercial) {
        comercialDAO.update(comercial);
    }

    public void deleteComercial(int id) {
        comercialDAO.delete(id);
    }
}


