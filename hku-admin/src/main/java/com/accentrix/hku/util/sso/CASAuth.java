package com.accentrix.hku.util.sso;

public final class CASAuth {
    private String UID;
    private String userEmail;
    private String HKUNO;
    private String userName;

    public CASAuth(String arg0, String arg1, String arg2, String arg3) {
        this.UID = arg0;
        this.userEmail = arg1;
        this.HKUNO = arg2;
        this.userName = arg3;
    }

    public CASAuth(String arg0, String arg1, String arg2) {
        this.UID = arg0;
        this.userEmail = arg1;
        this.HKUNO = arg2;
    }

    public final String getUID() {
        return this.UID;
    }

    public final String getUserEmail() {
        return this.userEmail;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final String getHKUNO() {
        return this.HKUNO;
    }
}