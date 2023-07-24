package com.ifpe.edu.paokentin.model.repositories;

import com.ifpe.edu.paokentin.model.entities.Pao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaoRepository implements GenericRepository<Pao, Integer> {

    protected PaoRepository() {
    }


    @Override
    public void create(Pao pao) throws SQLException {

        String sql = "insert into pao(tipo_pao, tempo_preparo, descricao) value(?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, pao.getTipoPao());
        pstm.setLong(2, pao.getTempoPreparo());
        pstm.setBytes(3, pao.getDescricao().getBytes());

        pstm.execute();
    }

    @Override
    public void update(Pao pao) throws SQLException {

        String sql = "update pao set tipo_pao=?, tempo_preparo=?, descricao=? where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, pao.getTipoPao());
        pstm.setLong(2, pao.getTempoPreparo());
        pstm.setString(3, pao.getDescricao());

        pstm.setInt(4, pao.getId());

        pstm.execute();
    }

    @Override
    public Pao read(Integer key) throws SQLException {
        String sql = "select * from pao where id= ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setInt(1, key);

        ResultSet result = pstm.executeQuery();
        Pao paoRead = null;

        if(result.next()){
            paoRead = new Pao();
            paoRead.setId(key);
            paoRead.setTipoPao(result.getString("tipo_pao"));
            paoRead.setTempoPreparo(result.getLong("tempo_preparo"));
            paoRead.setDescricao(result.getString("descricao"));
        }
        return paoRead;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        String sql = "delete from pao where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, key);
        pstm.execute();

    }

    @Override
    public List<Pao> readAll() throws SQLException {
        String sql = "select * from pao";

        List<Pao> paes = new ArrayList<>();

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        ResultSet result = pstm.executeQuery();

        Pao paoRead = null;

        while(result.next()){
            paoRead = new Pao();
            paoRead.setId(result.getInt("id"));
            paoRead.setTipoPao(result.getString("tipo_pao"));
            paoRead.setTempoPreparo(result.getLong("tempo_preparo"));
            paoRead.setDescricao(result.getString("descricao"));

            paes.add(paoRead);
        }
        return paes;
    }
}
