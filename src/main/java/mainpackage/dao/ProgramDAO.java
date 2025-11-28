package mainpackage.dao;

import mainpackage.model.DBConnection;
import mainpackage.model.Program;          

import java.sql.*;
import java.util.*;

public class ProgramDAO {

   
	public List<Program> findAll() throws SQLException {
        String sql = "SELECT id, voiceTime, sms, data, price, extraVoice, extraSms, extraData FROM program";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Program> list = new ArrayList<>();
            while (rs.next()) {
                Program p = new Program(
                  rs.getInt("id"),
                  rs.getInt("voiceTime"),
                  rs.getInt("sms"),
                  rs.getInt("data"),
                  rs.getFloat("price"),
                  rs.getInt("extraVoice"),
                  rs.getInt("extraSms"),
                  rs.getInt("extraData")
                );
                list.add(p);
            }
            return list;
        }
    }

    public Program findById(int id) throws SQLException {
        String sql = "SELECT id, voiceTime, sms, data, price, extraVoice, extraSms, extraData "
                   + "FROM program WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Program p = new Program();
                    p.setId(rs.getInt("id"));
                    p.setVoiceTime(rs.getInt("voiceTime"));
                    p.setSms(rs.getInt("sms"));
                    p.setData(rs.getInt("data"));
                    p.setPrice(rs.getFloat("price"));
                    p.setExtraVoice(rs.getInt("extraVoice"));
                    p.setExtraSms(rs.getInt("extraSms"));
                    p.setExtraData(rs.getInt("extraData"));
                    return p;
                }
                return null;
            }
        }
    }

	
	public void insert(Program p) throws SQLException {
        String sql = "INSERT INTO program"
                   + "(voiceTime, sms, data, price, extraVoice, extraSms, extraData) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt   (1, p.getVoiceTime());
            ps.setInt   (2, p.getSms());
            ps.setInt   (3, p.getData());
            ps.setFloat (4, p.getPrice());
            ps.setInt   (5, p.getExtraVoice());
            ps.setInt   (6, p.getExtraSms());
            ps.setInt   (7, p.getExtraData());
            ps.executeUpdate();
        }
    }
	
	
	public void update(Program p) throws SQLException {
        String sql = "UPDATE program SET voiceTime=?, sms=?, data=?, price=?, "
                   + "extraVoice=?, extraSms=?, extraData=? WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt   (1, p.getVoiceTime());
            ps.setInt   (2, p.getSms());
            ps.setInt   (3, p.getData());
            ps.setFloat (4, p.getPrice());
            ps.setInt   (5, p.getExtraVoice());
            ps.setInt   (6, p.getExtraSms());
            ps.setInt   (7, p.getExtraData());
            ps.setInt   (8, p.getId());
            ps.executeUpdate();
        }
    }
}
