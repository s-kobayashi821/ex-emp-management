package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 従業員情報を操作するコントローラ.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧を出力する．
     *
     * @param model リクエストスコープ
     * @return 従業員一覧ページにフォワードする
     */
    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }


    /**
     * 従業員詳細ページにフォワードする.
     *
     * @param id 従業員ID
     * @param model リクエストスコープ
     * @param form フォーム
     * @return 従業員詳細ページ
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.valueOf(id));
        model.addAttribute("employee", employee);
        return "employee/detail";
    }
}
