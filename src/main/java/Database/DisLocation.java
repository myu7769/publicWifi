package Database;

import TbPublicWifiInfo.Wifi;

public class DisLocation {

    Wifi wifi;
    Double distance;

    public Wifi getWifi() {
        return wifi;
    }

    public void setWifi(Wifi wifi) {
        this.wifi = wifi;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public DisLocation(Wifi wifi, Location location) {
        this.wifi = wifi;

        double r = 6371; // 지구의 반지름(km)
        double dLat = Math.toRadians(Double.parseDouble(this.wifi.getLAT()) - Double.parseDouble(location.getLat()));
        double dLnt = Math.toRadians(Double.parseDouble(this.wifi.getLNT()) - Double.parseDouble(location.getLnt()));
        double tmp1 = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(Double.parseDouble(this.wifi.getLAT())))
                * Math.cos(Math.toRadians(Double.parseDouble(location.getLat())))
                * Math.sin(dLnt / 2) * Math.sin(dLnt / 2);
        double tmp2 = 2 * Math.atan2(Math.sqrt(tmp1), Math.sqrt(1 - tmp1));
        this.distance = r * tmp2;

    }
}
