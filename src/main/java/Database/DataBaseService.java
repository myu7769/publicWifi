package Database;
import TbPublicWifiInfo.Wifi;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseService {

    String dbId = "test";
    String dbPwd = "password";
    // 데이터베이스 파일의 경로를 지정합니다.
    String url = "jdbc:mariadb://localhost:3306/db1";

    public void getWifiList() {

            Connection conn = null;
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;
            String whereId = "ttttt";

            // 좌표값 가까운 리스트들 조회해야됨

            try {
                // SQLite JDBC 드라이버를 로드합니다.
                Class.forName("org.mariadb.jdbc.Driver");

                // 데이터베이스에 연결합니다.
                conn = DriverManager.getConnection(url,dbId,dbPwd);

                // 쿼리를 실행합니다.
                String sql = " select id, name, password, age  from member " +
                        " where id = ? ";

                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, whereId);

                rs = preparedStatement.executeQuery();

                // 결과를 처리합니다.
                while (rs.next()) {
                    // 결과 처리 코드
                    String memberId = rs.getString("id");
                    String userName = rs.getString("name");
                    String password = rs.getString("password");
                    String age = rs.getString("age");

                    System.out.println(memberId +", " + userName + ", " + password + ", " + age);

                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }finally {
                // 연결을 닫습니다.
                try {
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if (preparedStatement != null && !preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
         }
    public void insertWifiList(ArrayList<Wifi> wifi) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        if(wifi == null){
            return;
        }


        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url,dbId,dbPwd);
            int affected = 0;
            for (int i = 0; i < wifi.size(); i++) {

            // 쿼리를 실행합니다.
                String sql = " REPLACE swifi (SWIFI_MGR_NO, SWIFI_WRDOFC, SWIFI_MAIN_NM, SWIFI_ADRES1, SWIFI_ADRES2, SWIFI_INSTL_FLOOR, SWIFI_INSTL_TY, SWIFI_INSTL_MBY, SWIFI_SVC_SE, SWIFI_CMCWR, SWIFI_CNSTC_YEAR, SWIFI_INOUT_DOOR, SWIFI_REMARS3, LAT, LNT, WORK_DTTM) VALUES " +
                    "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";

                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, wifi.get(i).getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, wifi.get(i).getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, wifi.get(i).getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, wifi.get(i).getX_SWIFI_ADRES1());
                preparedStatement.setString(5, wifi.get(i).getX_SWIFI_ADRES2());
                preparedStatement.setString(6, wifi.get(i).getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, wifi.get(i).getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, wifi.get(i).getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, wifi.get(i).getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, wifi.get(i).getX_SWIFI_CMCWR());
                preparedStatement.setString(11, wifi.get(i).getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, wifi.get(i).getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, wifi.get(i).getX_SWIFI_REMARS3());
                preparedStatement.setString(14, wifi.get(i).getLAT());
                preparedStatement.setString(15, wifi.get(i).getLNT());
                preparedStatement.setString(16, wifi.get(i).getWORK_DTTM());

                affected += preparedStatement.executeUpdate();
            }

            // 결과를 처리합니다.
            if(affected == wifi.size()){
                System.out.println("Wifi insert 성공");
            }else{
                System.out.println("Wifi insert 실패");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // 연결을 닫습니다.
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
