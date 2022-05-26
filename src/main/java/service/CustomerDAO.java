package service;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {


    public CustomerDAO() {
    }

    protected Connection getConnection() {
        //connection là class của thư viện ,mới thêm vào
        //kết nối dự án jave với CSDL
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");       //Đường truyền kết nối CSDL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2006?useSSL=false", "root", "123456");        //jdbcURL:đường truyền đến mySQL, jdbcUsername:, jdbcPassword
//       connection cần DriverManager để kết nối được với đường truyền

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void add(Customer customer) {

    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();

        Connection connection = getConnection();
        try {
            String sql = "select * from customer";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");
                Customer customer = new Customer(id, name, age);

                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    private void printSQLException(SQLException e) {
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return false;
    }
}
