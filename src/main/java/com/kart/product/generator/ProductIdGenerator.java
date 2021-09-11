package com.kart.product.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


public class ProductIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		String prefix = "P";
		String suffix = "";
		Connection connection = session.connection();

		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("select MAX(prodid) AS Id From product;");
			if(rs != null) {

				if(rs.next())
				{
					String temp = rs.getString("Id").substring(1);
					int id = Integer.parseInt(temp) + 1;
					suffix = String.valueOf(id);
					return prefix + suffix;
				} 
			}
			else
			{
				//String temp = rs.getString("Id").substring(1);
				int id = 101;
				suffix = String.valueOf(id);
				return prefix + suffix;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

}