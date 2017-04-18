package com.example.thirtyseven.mydog;

import java.util.Random;

/**
 * Created by ThirtySeven on 12.04.2017.
 */

class Dog {


    int mood;
    final Random random = new Random();

    public Dog(int mood) {
        this.mood = mood;
    }

    Dog() {
        mood = 50;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int beat() {
        int a = random.nextInt(10);
        if (mood - a <= 0) {
            mood = 0;
        } else {
            mood = mood - a;
        }
        return mood;
    }

    public int touch() {
        int a = random.nextInt(10);
        if (mood + a >= 100) {
            mood = 100;
        } else {
            mood = mood + a;
        }
        return mood;
    }

    public int care() {
        int a = random.nextInt((10)+3);
        if (mood + a >= 100) {
            mood = 100;
        } else {
            mood = mood + a;
        }
        return mood;
    }

    public boolean willBite() {
        int a = random.nextInt(1000);
        if(a % 2 == 0){
            return true;
        }else return false;
    }
}
