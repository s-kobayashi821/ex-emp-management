package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全件取得する．
     *
     * @return 取り出した従業員情報を返す
     */
    public List<Employee> showList(){
        return employeeRepository.findAll();
    }

    /**
     * 従業員情報を取得し，従業員情報をそのまま呼び出し手元に返す.
     *
     * @param id 従業員のID
     * @return 従業員情報
     */
    public Employee showDetail(Integer id){
        Employee employee = employeeRepository.findById(id);
        return  employee;
    }


    /**
     * 従業員情報を更新する.
     *
     * @param employee　従業員情報
     */
    public void update(Employee employee){
        employeeRepository.update(employee);
    }


}
