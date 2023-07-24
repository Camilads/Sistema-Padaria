package com.ifpe.edu.paokentin.model.repositories;

import com.ifpe.edu.paokentin.model.entities.Fornada;
import com.ifpe.edu.paokentin.model.entities.Pao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FornadaRepository implements GenericRepository<Fornada, Integer> {


    @Override
    public void create(Fornada fornada) throws SQLException {

        String sql = "insert into fornada (pao_id, horario_inicio, pronta) values (?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setInt(1, fornada.getPao().getId());
        pstm.setTimestamp(2, Timestamp.valueOf(fornada.getHoraInicio().toLocalDateTime()));
        pstm.setBoolean(3, fornada.isPronta());

        pstm.execute();
    }

    @Override
    public void update(Fornada fornada) throws SQLException {

        String sql = "update fornada set  pronta=? where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setBoolean(1, fornada.isPronta());
        pstm.setInt(2, fornada.getId());

        pstm.execute();

    }

    @Override
    public Fornada read(Integer key) throws SQLException {

        String sql = "select * from fornada where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setInt(1, key);

        ResultSet result = pstm.executeQuery();
        Fornada fornadaRead = null;

        if (result.next()) {
            fornadaRead = new Fornada();
            fornadaRead.setId(key);

            PaoRepository paoRepository = new PaoRepository();
            Pao pao = paoRepository.read(result.getInt("pao_id"));
            fornadaRead.setPao(pao);

            fornadaRead.setHoraInicio(result.getTimestamp("horario_inicio"));
            fornadaRead.setPronta(result.getBoolean("pronta"));
        }

        return fornadaRead;
    }

    @Override
    public void delete(Integer key) throws SQLException {

        String sql = "delete from fornada where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, key);
        pstm.execute();

    }

    @Override
    public List<Fornada> readAll() throws SQLException {
        String sql = "select * from fornada";

        List<Fornada> fornadas = new ArrayList<>();

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        ResultSet result = pstm.executeQuery();

        while (result.next()) {
            Fornada fornada = new Fornada();
            fornada.setId(result.getInt("id"));

            PaoRepository paoRepository = new PaoRepository();
            Pao pao = paoRepository.read(result.getInt("pao_id"));
            fornada.setPao(pao);

            fornada.setHoraInicio(result.getTimestamp("horario_inicio"));
            fornada.setPronta(result.getBoolean("pronta"));

            fornadas.add(fornada);
        }

        return fornadas;
    }

    public Fornada getLastProntaFornada() throws SQLException {
        String sql = "SELECT * FROM fornada WHERE pronta = 1 ORDER BY horario_inicio DESC LIMIT 1";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            Fornada fornada = new Fornada();
            fornada.setId(result.getInt("id"));

            PaoRepository paoRepository = new PaoRepository();
            Pao pao = paoRepository.read(result.getInt("pao_id"));
            fornada.setPao(pao);

            fornada.setHoraInicio(result.getTimestamp("horario_inicio"));
            fornada.setPronta(result.getBoolean("pronta"));

            return fornada;
        }

        return null;
    }
}
