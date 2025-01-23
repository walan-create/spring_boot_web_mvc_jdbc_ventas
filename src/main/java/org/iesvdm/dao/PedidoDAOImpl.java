package org.iesvdm.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
