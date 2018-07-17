/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dal;

import com.context.DBContext;
import com.model.Outcome;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class OutcomeDAO {
    // select by name
    public List<Outcome> selectByName(String name) throws Exception{
        Connection con = new DBContext().getConnection();
        String query = "SELECT * FROM dbo.Outcomes WHERE ship LIKE '%"+name+"%'";
        PreparedStatement ps = con.prepareStatement(query);
        List<Outcome> outcomes = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String ship = rs.getString("ship");
            String battle = rs.getString("battle");
            String result = rs.getString("result");
            outcomes.add(new Outcome(ship, battle, result));
        }
        rs.close();
        con.close();
        return outcomes;
    }
}
