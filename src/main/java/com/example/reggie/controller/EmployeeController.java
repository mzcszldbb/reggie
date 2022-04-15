package com.example.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.reggie.common.R;
import com.example.reggie.entity.Employee;
import com.example.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpSession session, @RequestBody Employee employee){
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee result = employeeService.getOne(wrapper);
        if(result == null){
            return R.error("用户名不存在！！");
        }
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        System.out.println(password);
        if(!password.equals(result.getPassword())){
            return R.error("密码错误！！");
        }
        if (result.getStatus() != 1){
            System.out.println("111");
            return R.error("该账号已被禁用！");
        }
        session.setAttribute("employee",result.getId());
        return R.success(result);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpSession session){
        session.removeAttribute("employee");
        return R.success("退出成功！");
    }

}
