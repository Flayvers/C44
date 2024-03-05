package com.example.exam1;

import java.util.Vector;

public class Groupe {
    private Vector<Evaluation> eval;
    public Groupe(){
        eval = new Vector<>();
    }

    public void addEval(Evaluation ev){
        this.eval.add(ev);
    }
    public int vectorLen(){
        return eval.size();
    }
    public double average(){
        double avgService = 0.0;
        for (Evaluation i : eval) {
            avgService += i.getServiceReussi();
        }
        avgService /= vectorLen();

        return avgService;
    }
    public String bestMat(){
        String r = null;
        double temp = 0;

        for (Evaluation i: eval){
            if (temp < i.getServiceReussi()){
                temp = i.getServiceReussi();
                r = i.getMatricule();
            }
        }
        return r;
    }

    public Vector<Evaluation> getEval() {
        return eval;
    }
}
