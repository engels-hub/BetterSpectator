package com.engels.betterspectator;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String Name;
    private boolean canSpectate;
    private List<String> Spectated;
    private String SpectateTarget;
    private Integer runnableID;
    private int KillCount;
    private int DeathCount;
    private double clicksPerSeconds;
    private Integer clicksID;


    public User(int ID, String NAME, boolean CANSPECTATE, ArrayList<String> S, String SPECTARGET, Integer RID, int DC, int KC, int CPS, Integer CID){
        this.setClicksID(CID);
        this.setId(ID);
        this.setName(NAME);
        this.setCanSpectate(CANSPECTATE);
        this.setSpectated(S);
        this.setSpectateTarget(SPECTARGET);
        this.setRunnableID(RID);
        this.setDeathCount(DC);
        this.setKillCount(KC);
        this.setClicksPerSeconds(CPS);

    }


    public int getDeathCount() {
        return DeathCount;
    }

    public void setDeathCount(int deathCount) {
        DeathCount = deathCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isCanSpectate() {
        return canSpectate;
    }

    public User setCanSpectate(boolean canSpectate) {
        this.canSpectate = canSpectate;
        return this;
    }

    public List<String> getSpectated() {
        return Spectated;
    }

    public void setSpectated(List<String> spectated) {
        Spectated = spectated;
    }

    public String getSpectateTarget() {
        return SpectateTarget;
    }

    public void setSpectateTarget(String spectateTarget) {
        SpectateTarget = spectateTarget;
    }

    public Integer getRunnableID() {
        return runnableID;
    }

    public void setRunnableID(Integer runnableID) {
        this.runnableID = runnableID;
    }

    public int getKillCount() {
        return KillCount;
    }

    public void setKillCount(int killCount) {
        KillCount = killCount;
    }

    public User incrementKillCount() {
        KillCount++;
        return this;
    }

    public User incrementDeathCount() {
        DeathCount++;
        return this;
    }

    public double getClicksPerSeconds() {
        return clicksPerSeconds;
    }

    public void setClicksPerSeconds(double clicksPerSeconds) {
        this.clicksPerSeconds = clicksPerSeconds;
    }

    public Integer getClicksID() {
        return clicksID;
    }

    public void setClicksID(Integer clicksID) {
        this.clicksID = clicksID;
    }
}
