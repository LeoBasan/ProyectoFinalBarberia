package com.aplicacion.login;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.turno.Turno;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginTemporal{
    private static final String FILE_PATH="src/main/resources/json/login.json";
    private Gson gson= new Gson();
    private static LoginTemporal instance;
    private List<Login> listLogin;

    private LoginTemporal(){
        loadLogin();
    }
    public static LoginTemporal getInstance(){
        if(instance==null){
            instance= new LoginTemporal();
        }
        return instance;
    }
    private void loadLogin(){
        try(Reader reader = new FileReader(FILE_PATH)){
            Type setType = new TypeToken<List<Login>>(){}.getType();
            listLogin = gson.fromJson(reader,setType);
            if(listLogin == null){
                listLogin = new ArrayList<>();
            }
        }catch (FileNotFoundException e ){
            listLogin= new ArrayList<>();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void saveLogin(){
        try(Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(listLogin,writer);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void addLogin(Login login){
        listLogin.clear();
        listLogin.add(login);
        saveLogin();
    }
    public List<Login> getListLogin() {
        return listLogin;
    }
}
