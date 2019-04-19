package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class UserDao {

	public void saveUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users (first_name, last_name, email) VALUES " + "('" + user.getFirstName()
					+ "', '" + user.getLastName() + "', '" + user.getEmail() + "') RETURNING id";
			Statement statement = conn.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				int id = rs.getInt("id");
				user.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void safeSaveUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users (first_name, last_name, email) VALUES " + 
		"(?, ?, ?) RETURNING id";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				user.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User getUserById(int id) {
		User user = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT first_name, last_name, email FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				user = new User(id, firstName, lastName, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public List<User> getUsersByLastName(String lastName) {
		List<User> users = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT id, first_name, email FROM users WHERE last_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, lastName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String email = rs.getString("email");
				users.add(new User(id, firstName, lastName, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
