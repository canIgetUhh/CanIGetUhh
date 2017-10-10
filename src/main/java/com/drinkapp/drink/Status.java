package com.drinkapp.drink;


public enum Status {
    INITIAL, IN_PROGRESS, COMPLETE;

    public static Status [] getAllStatuses() {
        return new Status[] {Status.INITIAL, Status.IN_PROGRESS, Status.COMPLETE};
    }
}
