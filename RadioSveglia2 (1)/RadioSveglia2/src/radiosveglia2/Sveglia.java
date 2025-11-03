/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package radiosveglia2;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;
import javax.sound.sampled.Clip;

public class Sveglia {

    private LocalTime orario;
    private LocalTime orarioSveglia;
    private LocalDate data;
    private String impostaFrequenza;
    private int volume;
    private boolean rinviaSveglia;
    private DateTimeFormatter ora = DateTimeFormatter.ofPattern(("HH:mm:ss"));
    private DateTimeFormatter dataForm = DateTimeFormatter.ofPattern(("YYYY-MM-dd"));
    private Clip clip;

    Random rdn = new Random();
    
    //costruttore della classe
    public Sveglia() {
        orario = LocalTime.now();
        data = LocalDate.now();
        volume = 5;
    }

    //setter e getter dell' ora
    public void setOrario(int ore, int min, int sec) {
        if (ore >= 0 && ore <= 23 && min >= 0 && min <= 59 && sec >= 0 && sec <= 59) {
            this.orario = LocalTime.of(ore, min, sec);
        }
    }
    public LocalTime getOrario() {
        return this.orario;
    }

    //setter e getter dell' ora della sveglia
    public void setOrarioSveglia(int ore, int min, int sec) {
        if (ore >= 0 && ore <= 23 && min >= 0 && min <= 59 && sec >= 0 && sec <= 59) {
            this.orarioSveglia = LocalTime.of(ore, min, sec);
        }
    }
    public LocalTime getOrarioSveglia() {
        return this.orarioSveglia;
    }

    //setter e getter della data
    public void setData(int anno, int mese, int giorno) {
        if (anno >= 0 && anno <= 9999 && mese >= 1 && mese <= 12 && giorno >= 1 && giorno <= 31) {
            try {
                this.data = LocalDate.of(anno, mese, giorno);
            } catch (Exception e) {}
        }
    }
    public LocalDate getData() {
        return this.data;
    }

    //setter e getter del volume
    public void setVolumePiu() {
        this.volume++;
        if(this.volume > 10){
            this.volume--;
        }
    }
    public void setVolumeMeno() {
        this.volume--;
        if(this.volume < 0){
            this.volume++;
        }
    }
    public int getVolume(){
        return this.volume;
    }
    
    //setter e getter della frequenza
    public void setFrequenza(String frequenza) {
        this.impostaFrequenza = frequenza;
    }
    public String getFrequenza() {
        return this.impostaFrequenza;
    }
    
    //setter e gettere del rinvia sveglia
    public void setRinviaSveglia(boolean rinvia) {
        this.rinviaSveglia = rinvia;
    }
    public boolean getRinviaSveglia() {
        return this.rinviaSveglia;
    }
    

    //spiegazione per me stesso che no ci arrivo a ricordare todo:faccio scorrere il tempo
    public String[] aumentaTempoSveglia() {
        String[] array=new String[2];
        this.orario = this.orario.plusSeconds(1);
        if(this.orario.equals(LocalTime.MIDNIGHT)){
            data.plusDays(1);
        }
        if(orario.equals(orarioSveglia)){
            songs();
        }
        array[0]=orario.format(ora);
        array[1]=data.format(dataForm);
        return array;
    }
    
    //faccio il random dell' orario, della data,dell' orario della sveglia, del volume, della stazione radio
    public ArrayList<String> random(){
        ArrayList<String> rand = new ArrayList<> ();
        
        //random delle ore
        int o = rdn.nextInt(0, 24);
        int m = rdn.nextInt(0, 60);
        int s = rdn.nextInt(0, 60);
        //ore memorizzate in un array
        int[] ore = new int[3];
        ore[0] = o;
        ore[1] = m;
        ore[2] = s;
        
        orario = LocalTime.of(ore[0], ore[1], ore[2]);
        orario.format(ora);
        rand.add(String.valueOf(orario));
        //random della data
        int aa = rdn.nextInt(1, 10000);
        int mm = rdn.nextInt(1, 13);
        int dd = rdn.nextInt(1, 29);
        //data memorizzata in un array
        int[] data = new int[3];
        data[0] = aa;
        data[1] = mm;
        data[2] = dd;
        this.data = LocalDate.of(data[0], data[1], data[2]);
        this.data = this.data.plusDays(rdn.nextInt(0, 4));
        this.data.format(dataForm);
        
        rand.add(String.valueOf(this.data));
        
        //random della sveglia
        int sveg = rdn.nextInt(2, 6);
        orarioSveglia = orario.plusMinutes(sveg);
        orarioSveglia.format(ora);
        rand.add(String.valueOf(orarioSveglia));
        
        //rand stazione
        int fr = rdn.nextInt(0,4);
        rand.add(String.valueOf(fr));
        
        //random volume
        int v = rdn.nextInt(0, 11);
        rand.add(String.valueOf(v));
        
        return rand;
    }
    
    //faccio suonare la sveglia
    public void songs(){
        try {
            File soundFile = new File(".\\src\\radiosveglia2\\song\\"+impostaFrequenza+".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);

            
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    //modifico il volume della sveglia quando suona
    public void setClipVolume() {
    if (clip != null && clip.isOpen()) {
        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();

            float percent = (volume / 10.0f) * 100;
            float dB = min + (max - min) * (percent / 100.0f);

            gainControl.setValue(dB);
        } catch (Exception e) {
            System.out.println("Controllo volume non supportato");
        }
    }
}
}