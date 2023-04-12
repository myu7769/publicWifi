package TbPublicWifiInfo;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

/*  요청 인자
     변수명	타입	변수설명	값설명
        KEY	String(필수)	인증키	OpenAPI 에서 발급된 인증키
        TYPE	String(필수)	요청파일타입	xml : xml, xml파일 : xmlf, 엑셀파일 : xls, json파일 : json
        SERVICE	String(필수)	서비스명	TbPublicWifiInfo
        START_INDEX	INTEGER(필수)	요청시작위치	정수 입력 (페이징 시작번호 입니다 : 데이터 행 시작번호)
        END_INDEX	INTEGER(필수)	요청종료위치	정수 입력 (페이징 끝번호 입니다 : 데이터 행 끝번호)
        X_SWIFI_WRDOFC	STRING(선택)	자치구	자치구명
        X_SWIFI_ADRES1	STRING(선택)	도로명주소	도로명 주소*/

/*공통	list_total_count	총 데이터 건수 (정상조회 시 출력됨)
        공통	RESULT.CODE	요청결과 코드 (하단 메세지설명 참고)
        공통	RESULT.MESSAGE	요청결과 메시지 (하단 메세지설명 참고)
        1	X_SWIFI_MGR_NO	관리번호
        2	X_SWIFI_WRDOFC	자치구
        3	X_SWIFI_MAIN_NM	와이파이명
        4	X_SWIFI_ADRES1	도로명주소
        5	X_SWIFI_ADRES2	상세주소
        6	X_SWIFI_INSTL_FLOOR	설치위치(층)
        7	X_SWIFI_INSTL_TY	설치유형
        8	X_SWIFI_INSTL_MBY	설치기관
        9	X_SWIFI_SVC_SE	서비스구분
        10	X_SWIFI_CMCWR	망종류
        11	X_SWIFI_CNSTC_YEAR	설치년도
        12	X_SWIFI_INOUT_DOOR	실내외구분
        13	X_SWIFI_REMARS3	wifi접속환경
        14	LAT	Y좌표
        15	LNT	X좌표
        16	WORK_DTTM	작업일자*/
public class WifiGetInfo {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode("684252656c6d7975353070676d6f63","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
        urlBuilder.append("/" + URLEncoder.encode("서대문구","UTF-8")); /* 서비스별 추가 요청인자들*/
        urlBuilder.append("/" + URLEncoder.encode("서소문로","UTF-8")); /* 서비스별 추가 요청인자들*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
