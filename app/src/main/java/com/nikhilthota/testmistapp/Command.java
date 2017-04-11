package com.nikhilthota.testmistapp;

public class Command {
    public Command(int a_volume, int lights, int sound, int deterrence){
        this.a_volume = a_volume;
        this.lights = lights;
        this.sound = sound;
        this.deterrence = deterrence;
    }

    String _id;
    String _rev;
    int a_volume;
    int lights;
    int sound;
    int deterrence;
}
