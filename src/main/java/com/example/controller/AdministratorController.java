package com.example.controller;

import com.example.form.InsertAdministatorForm;
import com.example.form.LoginForm;
import com.example.repository.AdministratorRepository;
import com.example.service.AdministratorService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者登録画面を表示する処理を喜寿する.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    /**
     *administrator/insert.htmlにフォワードする処理を記述する.
     *
     * @param form　従業員登録する際の入力フォーム
     * @return フォワード処理
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministatorForm form){
        return "administrator/insert";
    }

    /**
     * "administrator/login.htmlにフォワードする処理を記述する.
     *
     * @param form ログインする際の入力フォーム
     * @return フォワード処理
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return  "administrator/login";
    }
}
