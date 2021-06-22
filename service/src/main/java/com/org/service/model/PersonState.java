package com.org.service.model;

public enum PersonState {

	 ADDED, INCHECK, APPROVED, ACTIVE;
	
    private static PersonState[] vals = values();
    
    public static PersonState next(PersonState personState)
    {
        return vals[(personState.ordinal()+1) % vals.length];
    }
}
