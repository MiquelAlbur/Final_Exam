package com.example.finalexam;

public class Task {//Classe de l'objecte Task
    private String _id,_name,_date;
    private boolean _done = false;
    public Task(){
    }
    public Task(String id,String name, String date, boolean done){
        this._id = id;
        this._name = name;
        this._date = date;
        this._done = done;
    }

    public void omplir(){
        this._id = "1";
        this._name = "Finish";
    }

    public String get_id(){
        return this._id;
    }
    public String get_name(){
        return this._name;
    }
    public String get_date(){
        return this._date;
    }
    public boolean get_done(){
        return this._done;
    }
    public void set_id(String id){
        this._id = id;
    }

    public void set_name(String name){
        this._name = name;
    }

    public void set_date(String date){
        this._date = date;
    }

    public void set_done(boolean done){
        this._done = done;
    }
}
