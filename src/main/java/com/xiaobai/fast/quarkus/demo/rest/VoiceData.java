package com.xiaobai.fast.quarkus.demo.rest;


/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class VoiceData {
    private String lang;
    private String area;
    private String voice;
    private String gender;
    private String support_emotion;
    private String role;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSupport_emotion() {
        return support_emotion;
    }

    public void setSupport_emotion(String support_emotion) {
        this.support_emotion = support_emotion;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public VoiceData() {
    }

    public VoiceData(String lang, String area, String voice, String gender, String support_emotion, String role) {
        this.lang = lang;
        this.area = area;
        this.voice = voice;
        this.gender = gender;
        this.support_emotion = support_emotion;
        this.role = role;
    }
}
