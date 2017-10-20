package com.drinkapp.drink;

import java.time.LocalDate;
import java.util.HashMap;

public class SessionManager {
    public static SessionManager global = new SessionManager();

    private  int nextID;

    public class SessionInfo {
        public int userId;
        public LocalDate date;

        public SessionInfo(int userId) {
            this.userId = userId;
            this.date = LocalDate.now();
        }

        public int getUserId() {
            return userId;
        }


        public LocalDate getDate() {
            return date;
        }

    }

    private HashMap<Integer, SessionInfo> allSessions;

    public SessionManager() {
        this.allSessions = new HashMap<>();
        this.nextID = 0;
    }

    public Integer createSession(int userId){
        SessionInfo info = new SessionInfo(userId);
        int sessionID = nextID++;
        allSessions.put(sessionID, info);
        return sessionID;
    }

    public SessionInfo getValidSession(int sessionId) {
        SessionInfo info = this.allSessions.get(sessionId);
        System.out.println("Inside getValidSession the prospective info is: " + info);
        if ((info != null) && (info.date.equals(LocalDate.now()))) {
            return info;
        } else {
            return null;
        }
    }

    public boolean sessionIsValid(int sessionId) {
        return getValidSession(sessionId) != null;
    }

    public void clearExpiredSessions() {
        HashMap<Integer, SessionInfo> validSessions = new HashMap<>();

        for (Integer key: allSessions.keySet()){
            if (sessionIsValid(key)){
                validSessions.put(key, allSessions.get(key));
            }
            this.allSessions = validSessions;
        }
    }

    public HashMap<Integer, SessionInfo> getAllSessions() {
        return allSessions;
    }

    public void setAllSessions(HashMap<Integer, SessionInfo> allSessions) {
        this.allSessions = allSessions;
    }
}
