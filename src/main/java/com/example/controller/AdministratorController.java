package com.example.controller;

import com.example.form.InsertAdministatorForm;
import com.example.repository.AdministratorRepository;
import com.example.service.AdministratorService;
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
     *admin/insert.html にフォワードする処理を記述する.
     *
     * @param form　従業員登録する際のリクエストパラメータ
     * @return フォワード処理
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministatorForm form){

    }
}
