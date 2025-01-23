package org.iesvdm.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
@Repository
//Utilizo lombok para generar el constructor
@AllArgsConstructor
public class PedidoDAOImpl implements PedidoDAO{

    @Autowired
    JdbcClient jdbcClient;

    @Override
    public void create(Pedido pedido) {
        //Para el ejercicio no es necesario el create

//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        int rowsUpdated = jdbcClient.sql("""
//				INSERT INTO pedido (nombre, apellido1, apellido2, comisión)
//				VALUES (?,?,?,?)
//				""")
//                .param(pedido.getFecha())
//                .param(pedido.getTotal())
//                .param(pedido.getId_cliente())
//                .param(pedido.getId_comercial())
//                .update(keyHolder);
//
//        pedido.setId(keyHolder.getKey().intValue());
//        log.info("Insertados {} registros",rowsUpdated);
    }

    @Override
    public List<Pedido> getAll() {

        String query = """
                SELECT * FROM pedido
                """;

        RowMapper<Pedido> rowMapperPedido = (rs, rowNum) -> new Pedido(
                rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                rs.getInt("id_cliente"),
                rs.getInt("id_comercial")
        );

        return jdbcClient.sql(query)
                .query(rowMapperPedido)
                .list();

    }

    @Override
    public List<Pedido> getAllByComercialId(int id) {

        String query = """
                SELECT * FROM pedido WHERE id_comercial = :id
                """;

        RowMapper<Pedido> rowMapperPedido = (rs, rowNum) -> new Pedido(
                rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                rs.getInt("id_cliente"),
                rs.getInt("id_comercial")
        );

        return jdbcClient.sql(query)
                .param("id",id)
                .query(rowMapperPedido)
                .list();

    }

    @Override
    public List<PedidoDTO> getAllDTOByComercialId(int id_comercial) {

        String query = """
            SELECT p.id, p.total, p.fecha, p.id_cliente, p.id_comercial, 
                   c.nombre AS nombreCliente, com.nombre AS nombreComercial
            FROM pedido p
            JOIN cliente c ON p.id_cliente = c.id
            JOIN comercial com ON p.id_comercial = com.id
            WHERE p.id_comercial = :id_comercial
            """;

        RowMapper<PedidoDTO> rowMapperPedidoDTO = (rs, rowNum) -> PedidoDTO.builder()
                .id(rs.getInt("id"))
                .total(rs.getDouble("total"))
                .fecha(rs.getDate("fecha"))
                .id_cliente(rs.getInt("id_cliente"))
                .id_comercial(rs.getInt("id_comercial"))
                .nombreCliente(rs.getString("nombreCliente"))
                .nombreComercial(rs.getString("nombreComercial"))
                .build();

        return jdbcClient.sql(query)
                .param("id_comercial", id_comercial)
                .query(rowMapperPedidoDTO)
                .list();
    }


    @Override
    public Optional<Pedido> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Pedido pedido) {

    }

    @Override
    public void delete(long id) {

    }
}
