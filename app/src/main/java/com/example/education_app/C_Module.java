package com.example.education_app;


public class C_Module
{
    String Modules_names , Modules_Keys;

    public C_Module(){

    }
    public C_Module(String modules_names, String modules_Keys) {
        Modules_names = modules_names;
        Modules_Keys = modules_Keys;
    }

    public String getModules_names() {
        return Modules_names;
    }

    public void setModules_names(String modules_names) {
        Modules_names = modules_names;
    }

    public String getModules_Keys() {
        return Modules_Keys;
    }

    public void setModules_Keys(String modules_Keys) {
        Modules_Keys = modules_Keys;
    }
}
