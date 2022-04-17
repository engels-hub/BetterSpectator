package com.engels.betterspectator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private int id;
    private String Name;
    private boolean canSpectate;
    private List<String> Spectated;
    private String SpectateTarget;
    private Integer runnableID;
    private int KillCount;
    private int DeathCount;
    private UUID lastDamager;
    private Boolean clip;

    public User(int ID, String NAME, boolean CANSPECTATE, ArrayList<String> S, String SPECTARGET, Integer RID, int DC, int KC, UUID lastDamager, Boolean clip){
        this.setClip(clip);
        this.setLastDamager(lastDamager);
        this.setId(ID);
        this.setName(NAME);
        this.setCanSpectate(CANSPECTATE);
        this.setSpectated(S);
        this.setSpectateTarget(SPECTARGET);
        this.setRunnableID(RID);
        this.setDeathCount(DC);
        this.setKillCount(KC);
    }


    public int getDeathCount() {
        return DeathCount;
    }

    public User setDeathCount(int deathCount) {
        this.DeathCount = deathCount;
        return this;
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

    public User setKillCount(int killCount) {
        this.KillCount = killCount;
        return this;
    }

    public User incrementKillCount() {
        KillCount++;
        return this;
    }

    public User incrementDeathCount() {
        DeathCount++;
        return this;
    }

    public UUID getLastDamager() {
        return lastDamager;
    }

    public User setLastDamager(UUID lastDamager) {
        this.lastDamager = lastDamager;
        return this;
    }

    public User setClip(Boolean clip) {
        this.clip=clip;
        return this;
    }

    public Boolean isClip() {
        return clip;
    }
}
