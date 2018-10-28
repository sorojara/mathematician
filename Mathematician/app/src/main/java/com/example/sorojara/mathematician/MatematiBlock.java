package com.example.sorojara.mathematician;
import java.util.*;

public class MatematiBlock {

    public ArrayList<Integer> lista;//lista de bloques
    public int bloques[];
    int text;//referencia a texto en nodos hoja
    int layO; //referencia a Layout padre
    boolean sub;//control para hojas
    public String structure;// estructura del bloque
    int counter; //contador de bloques
    /*
     * EJEMPLO DE ESTRUCTURA
     * structure:
     * (4)/(20)*(_0 )
     */

    public MatematiBlock() {
        lista = new ArrayList<Integer>();
        text=-1;
        sub=false;
        structure = "";
        counter = 0;
        bloques = new int[0];
        layO = -1;
    }

    public MatematiBlock(int t, boolean b, String st, int c) {
        lista = new ArrayList<Integer>();
        text=t;
        sub=b;
        structure = st;
        counter = c;
    }

    public MatematiBlock(int t, boolean b, String st, int c, int l) {
        lista = new ArrayList<Integer>();
        text=t;
        sub=b;
        structure = st;
        counter = c;
        layO = l;
    }
    /* ADD YOUR CODE HERE */


  /*
  public String getText(){
    int i, j;

    if(sub==false){
      return text;
    }else{
      System.out.println("");
      ArrayList<Integer> structValues = new ArrayList<>();

      String firstList[] = structure.split("_");
      String buffer = firstList[0];

      for(i=1;i<firstList.length;i++){
        String secondList[] = firstList[i].split(" ");
        int num = Integer.parseInt(secondList[0]);
        structValues.add(num);
        String texto = bloques[num].getText();
        buffer = buffer + texto;

        for(j=1; j<secondList.length;j++){
          buffer = buffer + secondList[j];
        }

      }



      return buffer;
    }
  }
  */

    public void addBlock(int r420){
        lista = new ArrayList<Integer>();
        for(int i=0; i<bloques.length; i++){
            lista.add(bloques[i]);
        }
        lista.add(r420);
        counter += 1;
        sub = true;
        bloques = new int[lista.size()];
        for(int j=0; j<bloques.length; j++){
            bloques[j]=lista.get(j);
        }
    }

    public void updateBlock(int r420, int i){
        bloques[i]=r420;
    }


    public void setText(int in){
        text = in;
    }

    public void setLayO(int in){
        layO = in;
    }
  /*
  public void setText(String in, int num){
    lista = new ArrayList<Mathematik>(Arrays.asList(bloques));
    Mathematik buffer = lista.get(num);
    buffer.setText(in);
    lista.set(num, buffer);
    bloques = lista.toArray(new Mathematik[lista.size()]);
  }
  */



    public void setStructure(String in){
        structure = in;
    }




}

