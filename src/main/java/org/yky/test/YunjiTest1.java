package org.yky.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.*;
import java.util.*;

/**
 * Created by lenovo on 2018/11/23.
 */
public class aaaaTest1 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://aaaadevelop.mysql.rds.aliyuncs.com/aaaaomsordergeneral?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
    static final String username = "aaaadevelop";
    static final String password = "aaaadevelopIdc123";
//    static final String url = "jdbc:mysql://aaaatest.mysql.rds.aliyuncs.com/aaaaomsordergeneral?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
//    static final String username = "aaaadb";
//    static final String password = "aaaaDb123";
    static final String table1 = "t_rg_goods_money";
    static final String table2 = "t_rg_goods_money_info";
    static final String table3 = "t_rg_order_note";

    static final String skuNos = "sku_nos";
    static final String hasNote = "note_select";
    static final String note = "last_note_content";
    static final String lameReasons = "lame_reasons";
    static final String allnote = "note_contents";
    static final String nid = "nid";

    static final String spilce1 = ",";
    static final String spilce2 = "!@#";

    static String hasNoteYes = "'Y'";
    static String hasNoteNo = "'N'";

    static final String sql1 = "SELECT id FROM t_rg_goods_money";

    private static String getSql2(int id) {
        return "SELECT group_concat(sku_no) " + skuNos + ", group_concat(lame_reason) " + lameReasons + " FROM `t_rg_goods_money_info` WHERE th_tk_id = " + id;
    }

    private static String getSql3(int id) {
        return "SELECT id " + nid + ", content " + note + ", (select group_concat(b.content) from t_rg_order_note b where b.business_id = " + id + ") " + allnote +
                " FROM `t_rg_order_note` " +
                "WHERE business_type = 2 AND business_id = " + id + " order by modify_time desc limit 1";
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            //conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();

            List<String> sqllist = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                try {
//                    System.out.println(getThesql(rs.getInt("id"), conn, stmt1, stmt2));
                    sqllist.add(getThesql(rs.getInt("id"), conn, stmt1, stmt2));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            FileUtils.writeLines(new File("D:/sql.txt"), sqllist);
            try {
                stmt.close();
            } catch (Exception ex) {

            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static String getThesql(int id, Connection con, Statement st1, Statement st2) throws SQLException {
        //语句主体
        StringBuffer sbf = new StringBuffer("update ");
        sbf.append(table1);
        sbf.append(" set ");
        String sql2 = getSql2(id);
        ResultSet rs1 = st1.executeQuery(sql2);
        if (rs1.next()) {
            try {
                String skunos = rs1.getString(skuNos).trim();
                if (skunos != null && !skunos.equals("")) {
                    String[] skun = skunos.split(",");
                    sbf.append(skuNos);
                    sbf.append(" = '");
                    if (skun.length > 0)
                        sbf.append(removeRep(skun, spilce1));
                    sbf.append("',");
                }
            } catch (Exception ex) {

            }
            try {
                String lameReason = rs1.getString(lameReasons).trim();
                if (lameReason != null && !lameReason.equals("")) {
                    String[] lameRea = lameReason.split(",");
                    sbf.append(lameReasons);
                    sbf.append(" = '");
                    if (lameRea.length > 0)
                        sbf.append(removeRep(lameRea, spilce1));
                    sbf.append("',");
                }
            } catch (Exception ex) {

            }
        }
        String sql3 = getSql3(id);
        ResultSet rs2 = st2.executeQuery(sql3);
        if (rs2.next()) {
            try {
                String not = rs2.getString(note).trim();
                if (note != null && !note.equals("")) {
                    sbf.append(note);
                    sbf.append(" = '");
                    sbf.append(changeTheWord(not));
                    sbf.append("',");
                }
            } catch (Exception ex) {

            }
            try {
                String not = rs2.getString(allnote).trim();
                if (allnote != null && !allnote.equals("")) {
                    String[] nots = not.split(",");
                    sbf.append(allnote);
                    sbf.append(" = '");
                    if (nots.length > 0)
                        sbf.append(changeTheWord(removeRep(nots, spilce2)));
                    sbf.append("',");
                }
            } catch (Exception ex) {

            }
            try {
                int nnid = rs2.getInt(nid);
                sbf.append(hasNote);
                sbf.append(" = ");
                if (nnid > 0)
                    sbf.append(hasNoteYes);
                else
                    sbf.append(hasNoteNo);
            } catch (Exception ex) {

            }
        } else {
            sbf.append(hasNote);
            sbf.append(" = ");
            sbf.append(hasNoteNo);
        }
        if (',' == sbf.charAt(sbf.length() - 1))
            sbf = sbf.deleteCharAt(sbf.length() - 1);
        sbf.append(" where id = ");
        sbf.append(id);
        sbf.append(";");
        return sbf.toString();
    }

    private static String changeTheWord(String word) {
        return word.replace("'", "''");
    }

    private static String removeRep(String[] rrsa, String spilce) {
        Set<String> slist = new LinkedHashSet(Arrays.asList(rrsa));
        StringBuffer sbf = new StringBuffer();
        for (String rsa : slist) {
            sbf.append(rsa);
            sbf.append(spilce);
        }
        int lastL = spilce.length();
        if (spilce.equals(sbf.substring(sbf.length() - lastL)))
            return sbf.substring(0, sbf.length() - lastL);
        else return sbf.toString();
    }


}
