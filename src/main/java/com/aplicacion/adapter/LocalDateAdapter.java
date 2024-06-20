package com.aplicacion.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> { //Adaptamos el tipo de dato LocalDate para adaptarlo y poder usarlo en el gson
    private  static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.value(localDate.format(formatter));
    }

    public LocalDate read(JsonReader jsonReader) throws IOException{
        return LocalDate.parse(jsonReader.nextString(),formatter);
    }


}
