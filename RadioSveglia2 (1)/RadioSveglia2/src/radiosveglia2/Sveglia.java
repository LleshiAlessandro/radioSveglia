/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package radiosveglia2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Sveglia {

    private LocalTime orario;
    private LocalTime orarioSveglia;
    private LocalDate data;
    private String impostaFrequenza;
    private boolean rinviaSveglia;
    private DateTimeFormatter ora = DateTimeFormatter.ofPattern(("HH:mm:ss"));
    private DateTimeFormatter dataForm = DateTimeFormatter.ofPattern(("YYYY-MM-dd"));

    Random rdn = new Random();
    
    public Sveglia() {

    }

    public void setOrario(int ore, int min, int sec) {
        if (ore >= 0 && ore <= 23 && min >= 0 && min <= 59 && sec >= 0 && sec <= 59) {
            this.orario = LocalTime.of(ore, min, sec);
        }
    }

    public LocalTime getOrario() {
        return this.orario;
    }

    public void setOrarioSveglia(int ore, int min, int sec) {
        if (ore >= 0 && ore <= 23 && min >= 0 && min <= 59 && sec >= 0 && sec <= 59) {
            this.orarioSveglia = LocalTime.of(ore, min, sec);
        }
    }

    public LocalTime getOrarioSveglia() {
        return this.orarioSveglia;
    }

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

    public int setVolume(int vol, int i) {
        if (vol == 10 && i == -1) {
            vol += i;
        }
        else if (vol == 0 && i == 1){
            vol += i;
        }
        else if(vol > 0 && vol < 10){
            vol += i;
        }
        return vol;
    }
    public void setFrequenza(String frequenza) {
        this.impostaFrequenza = frequenza;
    }

    public String getFrequenza() {
        return this.impostaFrequenza;
    }

    public void setRinviaSveglia(boolean rinvia) {
        this.rinviaSveglia = rinvia;
    }

    public boolean getRinviaSveglia() {
        return this.rinviaSveglia;
    }

//spiegazione per me stesso che no ci arrivo a ricordare todo:aggiunge un minuto e fa i controlli per i 59 min da solo
    public String[] aumentaTempoSveglia() {
        String[] array=new String[2];
        this.orario = this.orario.plusSeconds(1);
        if(this.orario.equals(LocalTime.MIDNIGHT)){
            data.plusDays(1);
        }
        
        array[0]=orario.format(ora);
        array[1]=data.format(dataForm);
        return array;
    }

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
}