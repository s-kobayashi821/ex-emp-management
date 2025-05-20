package com.example.repository;

import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * employeesテーブルを操作するリポジトリクラス.
 */
@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER =
            (rs, i) ->{
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hireDate") );
        employee.setMailAddress(rs.getString("mailAddress"));
        employee.setZipCode(rs.getString("zipCode"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependentsCount"));
        return employee;
    };

    /**
     * 授業員一覧情報を入社日順(降順)で取得する(従業員が存在しない場合はサイズ0件の従業員一覧を返す).
     *
     * @return 従業員一覧情報(入社日の降順)
     */
    public List<Employee> findAll(){
        String sql = "SELECT id, name, image, gender, hireDate, mailAddress, zipCode, " +
                "address, telephone, salary, characteristics, dependentsCount FROM employees " +
                "ORDER BY hireDate DESC;";
        List<Employee>  employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
        return  employeeList;
    }

    /**
     * 主キーから従業員情報を取得する（従業員情報が存在しない場合はSpringが自動的に例外を発生する）．
     *
     * @param id 従業員ID
     * @return 検索した従業員情報
     */
    public Employee findById(Integer id){
        String sql = "SELECT id, name, image, gender, hireDate, mailAddress, zipCode, address, " +
                "telephone, salary, characteristics, dependentsCount FROM employees " +
                "WHERE id=:id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /**
     * 従業員を変更する(idカラムをのぞいた従業員情報のすべてのカラムを更新できるようなSQLを発行する)．
     * 全行更新されないようにWhere句の指定を考える．
     *
     * @param employee 変更結果とする従業員の情報
     */
    public void update(Employee employee){
        String sql = "SET employees UPDATE name=:name, image=:image, gender=:gender, hireDate=:hireDate, " +
                "mailAddress=:mailAddress, zipCode=:zipCode, address=:address, " +
                "telephone=:telephone, salary=:salary, characteristics=:characteristics, dependentsCount=:dependentsCount) " +
                "WHERE id=:id;";
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        template.update(sql, param);
    }


}
