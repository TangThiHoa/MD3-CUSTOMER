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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(id, name, age) values (?, ?, ?)");) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Customer findById(int id) {
        List<Customer> customers = findAll();
        Customer customer = new Customer();
        for (Customer cus : customers) {
            if (id == cus.getId()) {
                customer = cus;

            }
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from customer order by age");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int email = Integer.parseInt(rs.getString("age"));
                customers.add(new Customer(id, name, email));
            }
        } catch (SQLException e) {
        }
        return customers;
    }


    private void printSQLException(SQLException e) {
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean xoa= false;
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from customer where id = ?");){
       preparedStatement.setInt(1,id);
       xoa = preparedStatement.executeUpdate()>0;
        }
        return xoa;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        boolean a = false;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("update customer set name= ? , age=? where id=?");) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setInt(3, customer.getId());
            a = preparedStatement.executeUpdate() > 0;

        }
        return a;
    }
}
