package com.example.gonglee.model;

public class Candidate {
    private String id;
    private String tournament;
    private String gender;
    private String category;
    private String name;
    private String group_or_work;
    private String image_url;

    public Candidate() {}

    public Candidate(String id, String tournament, String gender, String category, 
                     String name, String group_or_work, String image_url) {
        this.id = id;
        this.tournament = tournament;
        this.gender = gender;
        this.category = category;
        this.name = name;
        this.group_or_work = group_or_work;
        this.image_url = image_url;
    }

    public String getId() { return id; }
    public String getTournament() { return tournament; }
    public String getGender() { return gender; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public String getGroup_or_work() { return group_or_work; }
    public String getImage_url() { return image_url; }

    public void setId(String id) { this.id = id; }
    public void setTournament(String tournament) { this.tournament = tournament; }
    public void setGender(String gender) { this.gender = gender; }
    public void setCategory(String category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setGroup_or_work(String group_or_work) { this.group_or_work = group_or_work; }
    public void setImage_url(String image_url) { this.image_url = image_url; }

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", group_or_work='" + group_or_work + '\'' +
                '}';
    }
}
