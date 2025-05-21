package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者情報を操作をするコントローラ.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    /**
     * administrator/login.htmlにフォワードする処理を記述する.
     *
     * @param form ログインする際の入力フォーム
     * @return フォワード処理
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return  "administrator/login";
    }

    /**
     * 管理者情報登録画面にフォワードする.
     *
     * @return 管理者情報登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param form　管理者情報の入力フォーム
     * @return ログイン画面にリダイレクトする
     */
    @GetMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/toInsert";
    }



    /**
     * ログイン処理を行い，成功したら従業員一覧情報ページにリダイレクトする.
     *
     * @param form ログインフォーム
     * @param model エラーメッセージのためのリクエストスコープ
     * @return 従業員一覧情報ページにリダイレクトする
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model){
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        //ログインが失敗したときの処理
        if(administrator == null){
            final String errorMessage = "メールアドレスまたはパスワードが不正です。";
            model.addAttribute("errorMessage", errorMessage);
            return "administrator/login";
        }

        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }

    /**
     * ログアウト処理を行い，最初のログイン画面にフォワードする.
     *
     * @param form ログイン画面の入力フォーム
     * @return ログイン画面にフォワードする
     */
    @GetMapping("/logout")
    public String logout(LoginForm form){
        session.invalidate();
        return toLogin(form);
    }
}
