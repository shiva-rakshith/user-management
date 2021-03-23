package org.rakshith.useractions.mysqluseractions;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.sql.Connection;

public class MysqlDBConfiguration {
        Config conf = ConfigFactory.load();
        String sqlURL = conf.getString("MysqlURL");
        String sqlUserName = conf.getString("MysqlUserName");
        String sqlPassword = conf.getString("MysqlPassword");

        String MySQLURL = sqlURL;
        String dbUserName = sqlUserName;
        String dbPassword = sqlPassword;
        Connection con = null;
}
