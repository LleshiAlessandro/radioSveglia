/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package radiosveglia2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Sveglia {

    private LocalTime orario;
    private LocalTime orarioSveglia;
    private LocalDate data;
    private int impostaVolume;
    private String impostaFrequenza;
    private boolean rinviaSveglia;
    private DateTimeFormatter ora = DateTimeFormatter.ofPattern(("HH:mm:ss"));
    private DateFormatter data = DateFormatter.ofPattern(("AAAA:MM:dd"));
    
    public Sveglia() {
        this.orario = LocalTime.of(0, 0, 0);
        this.orarioSveglia = LocalTime.of(0, 0, 0);
        this.data = LocalDate.now();
        this.impostaVolume = 5;
        this.impostaFrequenza = "FM";
        this.rinviaSveglia = false;
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

    public void setVolume(int vol) {
        if (vol >= 0 && vol <= 10) {
            this.impostaVolume = vol;
        }
    }

    public int getVolume() {
        return this.impostaVolume;
    }

    public int aumentaVol() {
        if (impostaVolume < 10) {
            impostaVolume++;
        }
        return impostaVolume;
    }

    public int diminuisciVol() {
        if (impostaVolume > 0) {
            impostaVolume--;
        }
        return impostaVolume;
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
        array[0]=data.format(ora);
        return ;
    }

    @Override
    public String toString() {
        return "Orario: " + orario +
               ", Sveglia: " + orarioSveglia +
               ", Data: " + data +
               ", Volume: " + impostaVolume +
               ", Frequenza: " + impostaFrequenza +
               ", Rinvia: " + rinviaSveglia;
    }
}