package Database;

import TbPublicWifiInfo.Wifi;
import org.checkerframework.checker.units.qual.A;

import java.sql.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DataBaseService {

    String dbId = "test";
    String dbPwd = "password";
    // 데이터베이스 파일의 경로를 지정합니다.
    String url = "jdbc:mariadb://localhost:3306/db1";

    static String lat;
    static String lnt;

    public static String getLat() {
        return lat;
    }

    public static void setLat(String lat) {
        DataBaseService.lat = lat;
    }

    public static String getLnt() {
        return lnt;
    }

    public static void setLnt(String lnt) {
        DataBaseService.lnt = lnt;
    }

    public ArrayList<Wifi> getWifiList() {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int count = 0;

        ArrayList<Wifi> wifis = new ArrayList<>();

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);

            // 쿼리를 실행합니다.
            String sql = " select SWIFI_MGR_NO, SWIFI_WRDOFC, SWIFI_MAIN_NM, SWIFI_ADRES1, " +
                    " SWIFI_ADRES2, SWIFI_INSTL_FLOOR, SWIFI_INSTL_TY, SWIFI_INSTL_MBY" +
                    ", SWIFI_SVC_SE, SWIFI_CMCWR, SWIFI_CNSTC_YEAR, SWIFI_INOUT_DOOR" +
                    ", SWIFI_REMARS3, LAT, LNT, WORK_DTTM from swifi ";

            preparedStatement = conn.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            // 결과를 처리합니다.
            while (rs.next()) {
                // 결과 처리 코드
                Wifi wifi = new Wifi(rs.getString("SWIFI_MGR_NO"), rs.getString("SWIFI_WRDOFC"), rs.getString("SWIFI_MAIN_NM"),
                        rs.getString("SWIFI_ADRES1"), rs.getString("SWIFI_ADRES2"), rs.getString("SWIFI_INSTL_FLOOR"),
                        rs.getString("SWIFI_INSTL_TY"), rs.getString("SWIFI_INSTL_MBY"), rs.getString("SWIFI_SVC_SE"),
                        rs.getString("SWIFI_CMCWR"), rs.getString("SWIFI_CNSTC_YEAR"), rs.getString("SWIFI_INOUT_DOOR"),
                        rs.getString("SWIFI_REMARS3"), rs.getString("LAT"), rs.getString("LNT"), rs.getString("WORK_DTTM"));


//                  System.out.println(memberId +", " + userName + ", " + password + ", " + age);
                wifis.add(wifi);
                count++;
            }
            System.out.println("wifis = " + wifis.size());
            System.out.println("count = " + count);

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            return wifis;
    }

    public void insertWifiList(ArrayList<Wifi> wifis) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        if (wifis == null) {
            return;
        }
        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;
            for (int i = 0; i < wifis.size(); i++) {

                // 쿼리를 실행합니다.
                String sql = " REPLACE swifi (SWIFI_MGR_NO, SWIFI_WRDOFC, SWIFI_MAIN_NM, SWIFI_ADRES1, SWIFI_ADRES2, SWIFI_INSTL_FLOOR, SWIFI_INSTL_TY, SWIFI_INSTL_MBY, SWIFI_SVC_SE, SWIFI_CMCWR, SWIFI_CNSTC_YEAR, SWIFI_INOUT_DOOR, SWIFI_REMARS3, LAT, LNT, WORK_DTTM) VALUES " +
                        "( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";

                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, wifis.get(i).getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, wifis.get(i).getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, wifis.get(i).getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, wifis.get(i).getX_SWIFI_ADRES1());
                preparedStatement.setString(5, wifis.get(i).getX_SWIFI_ADRES2());
                preparedStatement.setString(6, wifis.get(i).getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, wifis.get(i).getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, wifis.get(i).getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, wifis.get(i).getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, wifis.get(i).getX_SWIFI_CMCWR());
                preparedStatement.setString(11, wifis.get(i).getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, wifis.get(i).getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, wifis.get(i).getX_SWIFI_REMARS3());
                preparedStatement.setString(14, wifis.get(i).getLAT());
                preparedStatement.setString(15, wifis.get(i).getLNT());
                preparedStatement.setString(16, wifis.get(i).getWORK_DTTM());

                affected += preparedStatement.executeUpdate();
            }

            // 결과를 처리합니다.

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

    public DisLocation[] getNearWifi(ArrayList<Wifi> wifis, Location initLocation){

        int Num = 20; // 가까운 wifi 수
        DisLocation[] wifiArray = new DisLocation[Num];
        PriorityQueue<DisLocation> locations = new PriorityQueue<>((x,y) -> Double.compare(x.getDistance(),y.getDistance()));

        // 좌표값 가까운 리스트들 조회해야됨
        for (int i = 0; i < wifis.size(); i++) {
            locations.offer(new DisLocation(wifis.get(i), initLocation));
        }
        for (int i = 0; i < wifiArray.length; i++) {
            wifiArray[i] = locations.poll();
        }
        return wifiArray;
    }

    public void saveUserLocation (){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        if (lat == null || lnt == null) {
            return;
        }
        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;

                // 쿼리를 실행합니다.
            String sql = " insert into userHistory (lat, lnt, checkData) values ( ?, ? , now());";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,lat);
            preparedStatement.setString(2,lnt);

            affected = preparedStatement.executeUpdate();

            // 결과를 처리합니다.
            if (affected == 1) {
                System.out.println("user DB 저장 성공");
            } else {
                System.out.println("user DB 저장 실패");
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
    public ArrayList<User> getUserHistory (){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<>();

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;

            // 쿼리를 실행합니다.
            String sql = " select id ,lat , lnt , checkData from userHistory; ";
            preparedStatement = conn.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            // 결과를 처리합니다.
            while (rs.next()) {
                // 결과 처리 코드
                User user = new User(rs.getString("id"), new Location(rs.getString("lat"), rs.getString("lnt")),rs.getString("checkData"));
                userList.add(user);
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

        return userList;
    }

    public boolean deleteUser(String id){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<>();

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;

            // 쿼리를 실행합니다.
            String sql = " delete from userHistory where id = ? ; ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            affected = preparedStatement.executeUpdate();

//            sql = " select count(*) from userHistory; ";
//            preparedStatement = conn.prepareStatement(sql);
//            rs = preparedStatement.executeQuery();
//
//            String check = rs.getString("count(*)");
//
//            System.out.println("check = " + check);
//            
//            if()
//            sql = " alter table userHistory AUTO_INCREMENT=1; ";


            if(affected == 1){
                System.out.println("삭제 성공");
                return true;
            }else{
                System.out.println("삭제 실패");
                return false;
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

        return true;
    }
    public Wifi getWifiDetails(String mgrNo) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Wifi wifi = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);

            // 쿼리를 실행합니다.
            String sql = "  select SWIFI_MGR_NO, SWIFI_WRDOFC, SWIFI_MAIN_NM, SWIFI_ADRES1" +
                    ", SWIFI_ADRES2, SWIFI_INSTL_FLOOR, SWIFI_INSTL_TY, SWIFI_INSTL_MBY " +
                    ", SWIFI_SVC_SE, SWIFI_CMCWR, SWIFI_CNSTC_YEAR, SWIFI_INOUT_DOOR " +
                    ", SWIFI_REMARS3, LAT, LNT, WORK_DTTM from swifi where SWIFI_MGR_NO = ? ";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mgrNo);

            rs = preparedStatement.executeQuery();


            // 결과를 처리합니다.
            // 결과 처리 코드
            while (rs.next()) {
                wifi = new Wifi(rs.getString("SWIFI_MGR_NO"), rs.getString("SWIFI_WRDOFC"), rs.getString("SWIFI_MAIN_NM"),
                        rs.getString("SWIFI_ADRES1"), rs.getString("SWIFI_ADRES2"), rs.getString("SWIFI_INSTL_FLOOR"),
                        rs.getString("SWIFI_INSTL_TY"), rs.getString("SWIFI_INSTL_MBY"), rs.getString("SWIFI_SVC_SE"),
                        rs.getString("SWIFI_CMCWR"), rs.getString("SWIFI_CNSTC_YEAR"), rs.getString("SWIFI_INOUT_DOOR"),
                        rs.getString("SWIFI_REMARS3"), rs.getString("LAT"), rs.getString("LNT"), rs.getString("WORK_DTTM"));
            }

            if(wifi == null){
                System.out.println("상세 정보 실패");
            }else{
                System.out.println("상세 정보 성공");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return wifi;
    }
    public void creatBookMark (String name, int sequence){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;

            // 쿼리를 실행합니다.
            String sql = " insert into bookmark (name, sequence, registerTime) values ( ?, ? , now());";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,sequence);

            affected = preparedStatement.executeUpdate();

            // 결과를 처리합니다.
            if (affected == 1) {
                System.out.println("user DB 저장 성공");
            } else {
                System.out.println("user DB 저장 실패");
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
    public ArrayList<BookMark> getAllBookMark() {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<BookMark> bookMarks = new ArrayList<>();

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);

            // 쿼리를 실행합니다.
            String sql = " select id, name, sequence, registerTime, alterTime from bookmark ";

            preparedStatement = conn.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            // 결과를 처리합니다.
            // 결과 처리 코드
            while (rs.next()) {
                bookMarks.add(new BookMark(rs.getInt("id"), rs.getString("name"), rs.getInt("sequence"),
                        rs.getString("registerTime"), rs.getString("alterTime")));

            }

            if(bookMarks == null){
                System.out.println("북마크 정보 실패");
            }else{
                System.out.println("북마크 정보 성공");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return bookMarks;
    }
    public boolean deleteBookMark(int id){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;

            // 쿼리를 실행합니다.
            String sql = " delete from bookmark where id = ? ; ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            affected = preparedStatement.executeUpdate();

//            sql = " select count(*) from userHistory; ";
//            preparedStatement = conn.prepareStatement(sql);
//            rs = preparedStatement.executeQuery();
//
//            String check = rs.getString("count(*)");
//
//            System.out.println("check = " + check);
//
//            if()
//            sql = " alter table userHistory AUTO_INCREMENT=1; ";


            if(affected == 1){
                System.out.println("삭제 성공");
                return true;
            }else{
                System.out.println("삭제 실패");
                return false;
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

        return true;
    }

    public BookMark getBookMarkById(int id) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        BookMark bookMark = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);

            // 쿼리를 실행합니다.
            String sql = "  select id, name, sequence, registerTime, alterTime from bookmark where id = ? ";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();


            // 결과를 처리합니다.
            // 결과 처리 코드
            while (rs.next()) {
                bookMark = new BookMark(rs.getInt("id"), rs.getString("name"), rs.getInt("sequence"),
                        rs.getString("registerTime"), rs.getString("alterTime"));
            }

            if(bookMark == null){
                System.out.println("상세 정보 실패");
            }else{
                System.out.println("상세 정보 성공");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return bookMark;
    }
    public BookMark updateBookMark(int id, String name, int sequence) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        BookMark bookMark = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);

            // 쿼리를 실행합니다.
            String sql = "  UPDATE bookmark SET name = ? , sequence = ?, alterTime = now() WHERE id = ? ; ";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, sequence);
            preparedStatement.setInt(3, id);

            rs = preparedStatement.executeQuery();


            // 결과를 처리합니다.
            // 결과 처리 코드
            while (rs.next()) {
                bookMark = new BookMark(rs.getInt("id"), rs.getString("name"), rs.getInt("sequence"),
                        rs.getString("registerTime"), rs.getString("alterTime"));
            }

            if(bookMark == null){
                System.out.println("상세 정보 실패");
            }else{
                System.out.println("상세 정보 성공");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return bookMark;
    }
    public void addBookMarkWifi (String bookmarkName, String SWIFI_MGR_NO){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;

            // 쿼리를 실행합니다.
            String sql = " insert into bookmark_wifi (bookmark_name , SWIFI_MGR_NO, registerTime) values ( ?, ? ,now());";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,bookmarkName);
            preparedStatement.setString(2,SWIFI_MGR_NO);

            affected = preparedStatement.executeUpdate();

            // 결과를 처리합니다.
            if (affected == 1) {
                System.out.println("bookmark_wifi DB 저장 성공");
            } else {
                System.out.println("bookmark_wifi DB 저장 실패");
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

    public ArrayList<BookMarkWikis> getAllBookMarkWifiS(){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<BookMarkWikis> bookMarkWikis = new ArrayList<>();

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);

            // 쿼리를 실행합니다.
            String sql = " SELECT id , bookmark_name, swifi.SWIFI_MGR_NO as SWIFI_MGR_NO," +
                    " swifi.SWIFI_MAIN_NM as SWIFI_MAIN_NM , bookmark_wifi.registerTime as registerTime " +
                    " FROM bookmark_wifi " +
                    " JOIN swifi ON bookmark_wifi.SWIFI_MGR_NO = swifi.SWIFI_MGR_NO; ";

            preparedStatement = conn.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            // 결과를 처리합니다.
            // 결과 처리 코드
            while (rs.next()) {
                bookMarkWikis.add(new BookMarkWikis(rs.getInt("id"),
                        rs.getString("bookmark_name") ,
                        rs.getString("SWIFI_MGR_NO"),
                        rs.getString("SWIFI_MAIN_NM"),
                        rs.getString("registerTime")));
            }

            if(bookMarkWikis == null){
                System.out.println("북마크 정보 실패");
            }else{
                System.out.println("북마크 정보 성공");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return bookMarkWikis;


    }

    public BookMarkWikis getBookMarkWifisById(String id) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        BookMarkWikis bookMarkWikis = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);

            // 쿼리를 실행합니다.
            String sql = " select id, bookmark_name, swifi.SWIFI_MGR_NO as SWIFI_MGR_NO, swifi.SWIFI_MAIN_NM as SWIFI_MAIN_NM, registerTime " +
                    " from bookmark_wifi JOIN swifi ON bookmark_wifi.SWIFI_MGR_NO = swifi.SWIFI_MGR_NO where id = ? ;";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            rs = preparedStatement.executeQuery();


            // 결과를 처리합니다.
            // 결과 처리 코드
            while (rs.next()) {
                bookMarkWikis = new BookMarkWikis(rs.getInt("id"), rs.getString("bookmark_name"), rs.getString("SWIFI_MGR_NO"),
                        rs.getString("SWIFI_MAIN_NM"), rs.getString("registerTime"));
            }

            if(bookMarkWikis == null){
                System.out.println("상세 정보 실패");
            }else{
                System.out.println("상세 정보 성공");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return bookMarkWikis;
    }
    public boolean deleteBookMarkWifiS(int id){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // SQLite JDBC 드라이버를 로드합니다.
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결합니다.
            conn = DriverManager.getConnection(url, dbId, dbPwd);
            int affected = 0;

            // 쿼리를 실행합니다.
            String sql = " delete from bookmark_wifi where id = ? ; ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            affected = preparedStatement.executeUpdate();


            if(affected == 1){
                System.out.println("삭제 성공");
                return true;
            }else{
                System.out.println("삭제 실패");
                return false;
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

        return true;
    }
}
