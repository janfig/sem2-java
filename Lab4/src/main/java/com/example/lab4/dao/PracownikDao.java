package com.example.lab4.dao;

import com.example.lab4.beans.Pracownik;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PracownikDao {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        //wstrzyknięcie przez metodę set
        this.template = template;
    }

    public int save(Pracownik p) {
        String sql = "insert into pracownik (nazwisko,pensja,firma) "
                + "values('" + p.getNazwisko() + "'," + p.getPensja()
                + ",'" + p.getFirma() + "')";
        return template.update(sql);
    }

    public int update(Pracownik p) {
        String sql = "update pracownik set "
                + "nazwisko = " + p.getNazwisko()
                + " pensja = " + p.getPensja()
                + " firma = " + p.getFirma()
                + " where 1";
        return template.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from pracownik where id = " + id;
        return template.update(sql);
    }

    public List<Pracownik> getAll() {
        return template.query("select * from pracownik",
                new RowMapper<Pracownik>() {
                    @Override
                    public Pracownik mapRow(ResultSet rs, int row)
                            throws SQLException {
                        Pracownik e = new Pracownik();
                        e.setId(rs.getInt(1));
                        e.setNazwisko(rs.getString(2));
                        e.setPensja(rs.getFloat(3));
                        e.setFirma(rs.getString(4));
                        return e;
                    }
                });
    }

    public Pracownik getPracownikById(int id) {
        String sql = "select * from pracownik where id=?";
        return template.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Pracownik.class)
        );
    }
}