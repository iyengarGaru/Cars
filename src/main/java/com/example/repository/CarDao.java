package com.example.repository;

import com.example.Operations.Dao;
import com.example.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarDao implements Dao<Car> {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Bean(initMethod = "init")
    private void postConstruct(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("CARS").usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(Car car){
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(car);
        jdbcInsert.execute(parameters);
    }


    @Override
    public Car load(int id){

        List<Car> cars = jdbcTemplate.query("SELECT * FROM CARS WHERE ID = ?", new Object[]{id}, (resultSet,i) ->{
            return toCar(resultSet);
        } );
        if(cars.size() == 1)
            return cars.get(0);
        return null;

    }

    @Override
    public void delete(int id){

        jdbcTemplate.update("delete from cars where id = ?", id);
    }

    @Override
    public void update(Car car){

        throw new UnsupportedOperationException();

    }
    @Override
    public List<Car> loadAll(){
        return jdbcTemplate.query("SELECT * FROM CARS",(resultSet, i)-> {
            return toCar(resultSet);
        } );
    }

    private Car toCar(ResultSet resultSet) throws SQLException{

        Car car = new Car();
        car.setID(resultSet.getInt("id"));
        car.setModelName(resultSet.getString("model_name"));
        car.setBrandName(resultSet.getString("brand_name"));
        car.setYear(resultSet.getInt("year"));
        return car;

    }
}
