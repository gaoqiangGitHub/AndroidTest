package com.example.androidtest.entity;

/**
 * @author alan
 * @version ${date}${time}
 * @description
 */
public class LoginInfoEntity {

    /**
     * status : true
     * info : {"userInfo":{"userId":"255","userNick":"韩威光","lastLoginInfo":"false","city":"35","cityName":"上海市","access":"1"},"stationInfo":{"lat":"31.21367","lng":"121.406576","station_name":"上海天山","faultTolerant":"3000","startwork":"9:00","endwork":"18:00"},"is_manager":"1","token":"8a154afda7bcadb85554c2284b1248016ef7f72b","dutyflag":0,"wandaFlag":false}
     */

    private String status;
    /**
     * userInfo : {"userId":"255","userNick":"韩威光","lastLoginInfo":"false","city":"35","cityName":"上海市","access":"1"}
     * stationInfo : {"lat":"31.21367","lng":"121.406576","station_name":"上海天山","faultTolerant":"3000","startwork":"9:00","endwork":"18:00"}
     * is_manager : 1
     * token : 8a154afda7bcadb85554c2284b1248016ef7f72b
     * dutyflag : 0
     * wandaFlag : false
     */

    private InfoEntity info;

    public void setStatus(String status) { this.status = status;}

    public void setInfo(InfoEntity info) { this.info = info;}

    public String getStatus() { return status;}

    public InfoEntity getInfo() { return info;}

    public static class InfoEntity {
        /**
         * userId : 255
         * userNick : 韩威光
         * lastLoginInfo : false
         * city : 35
         * cityName : 上海市
         * access : 1
         */

        private UserInfoEntity userInfo;
        /**
         * lat : 31.21367
         * lng : 121.406576
         * station_name : 上海天山
         * faultTolerant : 3000
         * startwork : 9:00
         * endwork : 18:00
         */

        private StationInfoEntity stationInfo;
        private String is_manager;
        private String token;
        private int dutyflag;
        private boolean wandaFlag;

        public void setUserInfo(UserInfoEntity userInfo) { this.userInfo = userInfo;}

        public void setStationInfo(StationInfoEntity stationInfo) { this.stationInfo = stationInfo;}

        public void setIs_manager(String is_manager) { this.is_manager = is_manager;}

        public void setToken(String token) { this.token = token;}

        public void setDutyflag(int dutyflag) { this.dutyflag = dutyflag;}

        public void setWandaFlag(boolean wandaFlag) { this.wandaFlag = wandaFlag;}

        public UserInfoEntity getUserInfo() { return userInfo;}

        public StationInfoEntity getStationInfo() { return stationInfo;}

        public String getIs_manager() { return is_manager;}

        public String getToken() { return token;}

        public int getDutyflag() { return dutyflag;}

        public boolean isWandaFlag() { return wandaFlag;}

        public static class UserInfoEntity {
            private String userId;
            private String userNick;
            private String lastLoginInfo;
            private String city;
            private String cityName;
            private String access;

            public void setUserId(String userId) { this.userId = userId;}

            public void setUserNick(String userNick) { this.userNick = userNick;}

            public void setLastLoginInfo(String lastLoginInfo) { this.lastLoginInfo = lastLoginInfo;}

            public void setCity(String city) { this.city = city;}

            public void setCityName(String cityName) { this.cityName = cityName;}

            public void setAccess(String access) { this.access = access;}

            public String getUserId() { return userId;}

            public String getUserNick() { return userNick;}

            public String getLastLoginInfo() { return lastLoginInfo;}

            public String getCity() { return city;}

            public String getCityName() { return cityName;}

            public String getAccess() { return access;}
        }

        public static class StationInfoEntity {
            private String lat;
            private String lng;
            private String station_name;
            private String faultTolerant;
            private String startwork;
            private String endwork;

            public void setLat(String lat) { this.lat = lat;}

            public void setLng(String lng) { this.lng = lng;}

            public void setStation_name(String station_name) { this.station_name = station_name;}

            public void setFaultTolerant(String faultTolerant) { this.faultTolerant = faultTolerant;}

            public void setStartwork(String startwork) { this.startwork = startwork;}

            public void setEndwork(String endwork) { this.endwork = endwork;}

            public String getLat() { return lat;}

            public String getLng() { return lng;}

            public String getStation_name() { return station_name;}

            public String getFaultTolerant() { return faultTolerant;}

            public String getStartwork() { return startwork;}

            public String getEndwork() { return endwork;}
        }
    }
}
