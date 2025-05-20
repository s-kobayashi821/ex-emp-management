package com.example.repository;

import com.example.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


/**
 * administoratorsテーブルを操作するリポジトリクラス.
 */
@Repository
public class AdministratorRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final RowMapper<Administrator> ADMINISTRATOR_LOW_MAPPER
            = (rs, i)->{
      Administrator administrator = new Administrator();
      administrator.setId(rs.getInt("id"));
      administrator.setName(rs.getString("name"));
      administrator.setMailAddress(rs.getString("mail_address"));
      administrator.setPassword(rs.getString("password"));
      return administrator;
    };

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator){
        String sql = "INSERT INTO administrators (id, name, mailAddress, password) VALUES (:id, :name, :mailAddress, :password);";
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        template.update(sql, param);
    }

    /**
     *メールアドレスとパスワードから管理者情報を取得する(1件も存在しない場合はnullを返す).
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 取り出した管理者情報を返す
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "SELECT id, name, mail_address, password FROM administrators WHERE mail_address=:mailAddress AND password=:password;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);

        //1件もヒットしなかったらnullを返すようにする例外処理
        try{
            return template.queryForObject(sql, param, ADMINISTRATOR_LOW_MAPPER);
        }catch (Exception e){
            return  null;
        }
    }
}
