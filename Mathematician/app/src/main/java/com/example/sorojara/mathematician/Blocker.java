package com.example.sorojara.mathematician;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.Color;


import java.util.*;


public class Blocker{
    public ArrayList<MatematiBlock> elementos;
    int counter;
    EditText blocks [];
    RelativeLayout groups[];
    Context c;
    int ids;
    int layoutCounter;
    RelativeLayout pantalla;




    public Blocker() {
        elementos = new ArrayList<MatematiBlock>();
        counter = 0;
        //elementos.add(creator(0));
        ids = 0;
        layoutCounter = 0;
    }

    @SuppressLint("ResourceType")
    public Blocker(Context in) {
        elementos = new ArrayList<MatematiBlock>();
        counter = 0;
        ids = 0;
        layoutCounter = 0;
        c=in;
        pantalla = new RelativeLayout(c);
        blocks = new EditText[1];
        groups = new RelativeLayout[1];

        View.OnClickListener botonPlot = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(getText(0));
                Intent i = new Intent(c, Plot.class);
                i.putExtra("key",getText(0));
                c.startActivity(i);
            }
        };

        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data=ClipData.newPlainText("","");
                View.DragShadowBuilder myShadow= new View.DragShadowBuilder(v);
                v.startDrag(data, myShadow, v, 0);
                return true;
            }
        };

        View.OnDragListener dragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent=event.getAction();
                final View view = (View) event.getLocalState();
                switch (dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(Color.rgb(255, 153, 153));

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(Color.rgb(255, 255, 255));
                        break;
                    case DragEvent.ACTION_DROP:
                        v.setEnabled(false);
                        System.out.println("ID: " + v.getId());

                        if(view.getId()==2000) {
                            int mat = getIblock(v.getId());
                            int layOut = elementos.get(mat).layO;
                            int ET = getIET(v.getId());

                            elementos.set(mat, creator(1));

                            //System.out.println("LayoutCounter: " + layoutCounter);
                            //System.out.println(groups[layoutCounter-2].getRootView());

                            groups[layoutCounter-2].setId(v.getId());

                            groups[layOut].removeView(blocks[ET]);
                            groups[layOut].addView(groups[layoutCounter-2]);


                        } else if(view.getId()==2002) {
                            int mat = getIblock(v.getId());
                            int layOut = elementos.get(mat).layO;
                            int ET = getIET(v.getId());

                            elementos.set(mat, creator(2));

                            //System.out.println("LayoutCounter: " + layoutCounter);
                            //System.out.println(groups[layoutCounter-2].getRootView());

                            groups[layoutCounter-2].setId(v.getId());

                            groups[layOut].removeView(blocks[ET]);
                            groups[layOut].addView(groups[layoutCounter-2]);

                        } else if(view.getId()==2003) {
                            int mat = getIblock(v.getId());
                            int layOut = elementos.get(mat).layO;
                            int ET = getIET(v.getId());

                            elementos.set(mat, creator(3));

                            //System.out.println("LayoutCounter: " + layoutCounter);
                            //System.out.println(groups[layoutCounter-2].getRootView());

                            groups[layoutCounter-2].setId(v.getId());

                            groups[layOut].removeView(blocks[ET]);
                            groups[layOut].addView(groups[layoutCounter-2]);

                        }

                        break;
                }
                return true;
            }
        };

        ImageView division = new ImageView(c);
        ImageView exponente = new ImageView(c);
        ImageView raiz = new ImageView(c);
        Button help = new Button(c);
        Button calc = new Button(c);
        blocks[0] = new EditText(c);
        groups[0] = new RelativeLayout(c);

        division.setImageResource(R.drawable.divlogo);
        exponente.setImageResource(R.drawable.explogo);
        raiz.setImageResource(R.drawable.radlogo);

        division.setOnLongClickListener(longClickListener);
        exponente.setOnLongClickListener(longClickListener);
        raiz.setOnLongClickListener(longClickListener);

        division.setId(counter + 2000);
        help.setId(counter + 2001);
        exponente.setId(2002);
        raiz.setId(2003);

        help.setText("Help");
        calc.setText("Plot");

        calc.setOnClickListener(botonPlot);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.LEFT_OF, 2002);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params2.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params3.addRule(RelativeLayout.RIGHT_OF, 2002);
        params3.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params4.addRule(RelativeLayout.ALIGN_LEFT);
        params4.addRule(RelativeLayout.BELOW, 2000);

        RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params5.addRule(RelativeLayout.BELOW, 2000);
        params5.addRule(RelativeLayout.RIGHT_OF, 2001);

        division.setLayoutParams(params);
        exponente.setLayoutParams(params2);
        raiz.setLayoutParams(params3);
        help.setLayoutParams(params4);
        calc.setLayoutParams(params5);

        pantalla.addView(division);
        pantalla.addView(exponente);
        pantalla.addView(raiz);
        pantalla.addView(help);
        pantalla.addView(calc);


        MatematiBlock n = new MatematiBlock(0, false, "", 0, 0);
        elementos.add(n);
        blocks[0].setId(0);
        groups[0].setId(420);


        //Chequear ubicacion de FILL PARENT ????
        RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params6.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params6.addRule(RelativeLayout.BELOW, 2001);

        RelativeLayout.LayoutParams params7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params7.addRule(RelativeLayout.CENTER_IN_PARENT);

        blocks[0].setLayoutParams(params7);
        blocks[0].setOnDragListener(dragListener);
        blocks[0].setHint("(   )");

        groups[0].addView(blocks[0]);
        groups[0].setLayoutParams(params6);

        pantalla.addView(groups[0]);

        ids=1;
        layoutCounter = 1;
        counter = 1;


    }


    @SuppressLint("ResourceType")
    public MatematiBlock creator(int op){
        MatematiBlock salida = new MatematiBlock();
        ArrayList<EditText>lista;

        RelativeLayout rl1= new RelativeLayout(c);
        RelativeLayout rl2= new RelativeLayout(c);
        rl2.setId(layoutCounter + 420);

        View.OnDragListener dragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent=event.getAction();
                final View view = (View) event.getLocalState();
                switch (dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(Color.rgb(255, 153, 153));

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(Color.rgb(255, 255, 255));
                        break;
                    case DragEvent.ACTION_DROP:
                        v.setEnabled(false);
                        System.out.println("ID: " + v.getId());

                        if(view.getId()==2000) {
                            int mat = getIblock(v.getId());
                            int layOut = elementos.get(mat).layO;
                            int ET = getIET(v.getId());

                            elementos.set(mat, creator(1));

                            //System.out.println("LayoutCounter: " + layoutCounter);
                            //System.out.println(groups[layoutCounter-2].getRootView());

                            groups[layoutCounter-2].setId(v.getId());

                            groups[layOut].removeView(blocks[ET]);
                            groups[layOut].addView(groups[layoutCounter-2]);


                        } else if(view.getId()==2002) {
                            int mat = getIblock(v.getId());
                            int layOut = elementos.get(mat).layO;
                            int ET = getIET(v.getId());

                            elementos.set(mat, creator(2));

                            //System.out.println("LayoutCounter: " + layoutCounter);
                            //System.out.println(groups[layoutCounter-2].getRootView());

                            groups[layoutCounter-2].setId(v.getId());

                            groups[layOut].removeView(blocks[ET]);
                            groups[layOut].addView(groups[layoutCounter-2]);

                        } else if(view.getId()==2003) {
                            int mat = getIblock(v.getId());
                            int layOut = elementos.get(mat).layO;
                            int ET = getIET(v.getId());

                            elementos.set(mat, creator(3));

                            //System.out.println("LayoutCounter: " + layoutCounter);
                            //System.out.println(groups[layoutCounter-2].getRootView());

                            groups[layoutCounter-2].setId(v.getId());

                            groups[layOut].removeView(blocks[ET]);
                            groups[layOut].addView(groups[layoutCounter-2]);

                        }

                        break;
                }
                return true;
            }
        };

        //operacion
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params3.addRule(RelativeLayout.CENTER_IN_PARENT);

        //izquierda
        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params4.addRule(RelativeLayout.CENTER_VERTICAL);
        params4.addRule(RelativeLayout.LEFT_OF, layoutCounter + 420);
        //params4.addRule(RelativeLayout.ALIGN_LEFT);

        //derecha

        RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params5.addRule(RelativeLayout.CENTER_VERTICAL);
        params5.addRule(RelativeLayout.RIGHT_OF, layoutCounter + 420);
        //params5.addRule(RelativeLayout.ALIGN_RIGHT);


        //counter = counter +1;
        switch(op){
            case 0://Bloque Base
                lista = new ArrayList<EditText>(Arrays.asList(blocks));
                lista.add(new EditText(c));
                blocks = lista.toArray(new EditText[lista.size()]);
                MatematiBlock centro = new MatematiBlock();

                elementos.add(centro);
                counter = counter +1;
                salida.addBlock(counter);
                salida.setStructure(" _0 ");
                salida.counter = 1;
                break;

            case 1://Bloque Division

                //ON DRAG DEBE ACTUALIZAR ESTO


                //PARAMETROS DE LAYOUT:
                //dividendo
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                //divisor
                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params2.addRule(RelativeLayout.BELOW, ids+2);
                //params2.addRule(RelativeLayout.B);


                lista = new ArrayList<EditText>(Arrays.asList(blocks));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                blocks = lista.toArray(new EditText[lista.size()]);

                blocks[ids].setId(ids);
                blocks[ids+1].setId(ids+1);
                blocks[ids+2].setId(ids+2);
                blocks[ids+3].setId(ids+3);

                blocks[ids].setOnDragListener(dragListener);
                blocks[ids+1].setOnDragListener(dragListener);
                blocks[ids+2].setOnDragListener(dragListener);
                blocks[ids+3].setOnDragListener(dragListener);

                blocks[ids].setLayoutParams(params4);
                blocks[ids+1].setLayoutParams(params5);
                blocks[ids+2].setLayoutParams(params);
                blocks[ids+3].setLayoutParams(params2);

                blocks[ids].setHint("(   )");
                blocks[ids+1].setHint("(   )");
                blocks[ids+2].setHint("(   )");
                blocks[ids+3].setHint("(   )");

                rl2.setLayoutParams(params3);




                MatematiBlock leftBlock = new MatematiBlock();
                leftBlock.setText(ids);
                leftBlock.setLayO(layoutCounter);

                elementos.add(leftBlock);
                salida.addBlock(counter);
                counter = counter +1;

                MatematiBlock rightBlock = new MatematiBlock();
                rightBlock.setText(ids+1);
                rightBlock.setLayO(layoutCounter);
                elementos.add(rightBlock);
                salida.addBlock(counter);
                counter = counter +1;

                MatematiBlock dividendo = new MatematiBlock();
                dividendo.setText(ids+2);
                dividendo.setLayO(layoutCounter+1);
                elementos.add(dividendo);
                salida.addBlock(counter);
                counter = counter +1;

                //SI DEJA DE SERVIR; DESCOMENTAR ESTO:
                // salida.addBlock(counter);

                MatematiBlock divisor = new MatematiBlock();
                divisor.setText(ids+3);
                divisor.setLayO(layoutCounter+1);
                elementos.add(divisor);
                salida.addBlock(counter);
                counter = counter +1;

                salida.setStructure(" _0 ((_2 )/(_3 )) _1 ");
                salida.counter = 4;
                salida.sub = true;

                ArrayList<RelativeLayout> grupos = new ArrayList<RelativeLayout>(Arrays.asList(groups));
                grupos.add(new RelativeLayout(c));
                grupos.add(new RelativeLayout(c));
                groups = grupos.toArray(new RelativeLayout[grupos.size()]);


                rl1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                groups[layoutCounter] = rl1;
                groups[layoutCounter+1] = rl2;

                groups[layoutCounter+1].addView(blocks[ids+2]);
                groups[layoutCounter+1].addView(blocks[ids+3]);

                groups[layoutCounter].addView(groups[layoutCounter+1]);
                groups[layoutCounter].addView(blocks[ids+0]);
                groups[layoutCounter].addView(blocks[ids+1]);


                ids += 4;
                layoutCounter += 2;
                break;

            case 2://Bloque Exponencial
                RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params6.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params6.addRule(RelativeLayout.BELOW, ids+3);


                //divisor
                RelativeLayout.LayoutParams params7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params7.addRule(RelativeLayout.RIGHT_OF, ids + 2);
                params7.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                lista = new ArrayList<EditText>(Arrays.asList(blocks));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                blocks = lista.toArray(new EditText[lista.size()]);

                blocks[ids].setId(ids);
                blocks[ids+1].setId(ids+1);
                blocks[ids+2].setId(ids+2);
                blocks[ids+3].setId(ids+3);

                blocks[ids].setOnDragListener(dragListener);
                blocks[ids+1].setOnDragListener(dragListener);
                blocks[ids+2].setOnDragListener(dragListener);
                blocks[ids+3].setOnDragListener(dragListener);

                blocks[ids].setLayoutParams(params4);
                blocks[ids+1].setLayoutParams(params5);
                blocks[ids+2].setLayoutParams(params6);
                blocks[ids+3].setLayoutParams(params7);

                blocks[ids].setHint("(   )");
                blocks[ids+1].setHint("(   )");
                blocks[ids+2].setHint("(   )");
                blocks[ids+3].setHint("(   )");

                rl2.setLayoutParams(params3);

                MatematiBlock leftBlock1 = new MatematiBlock();
                leftBlock1.setText(ids);
                leftBlock1.setLayO(layoutCounter);
                elementos.add(leftBlock1);
                salida.addBlock(counter);
                counter = counter +1;

                MatematiBlock rightBlock1 = new MatematiBlock();
                rightBlock1.setText(ids+1);
                rightBlock1.setLayO(layoutCounter);
                elementos.add(rightBlock1);
                salida.addBlock(counter);
                counter = counter +1;


                MatematiBlock base = new MatematiBlock();
                base.setText(ids+2);
                base.setLayO(layoutCounter+1);
                elementos.add(base);
                salida.addBlock(counter);
                counter = counter +1;

                MatematiBlock exponente = new MatematiBlock();
                exponente.setText(ids+3);
                exponente.setLayO(layoutCounter+1);
                elementos.add(exponente);
                salida.addBlock(counter);
                counter = counter +1;

                salida.sub = true;
                salida.setStructure(" _0 ((_2 )^(_3 )) _1 ");
                salida.counter = 4;

                ArrayList<RelativeLayout> grupos2 = new ArrayList<RelativeLayout>(Arrays.asList(groups));
                grupos2.add(new RelativeLayout(c));
                grupos2.add(new RelativeLayout(c));
                groups = grupos2.toArray(new RelativeLayout[grupos2.size()]);

                rl1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                groups[layoutCounter] = rl1;
                groups[layoutCounter+1] = rl2;

                groups[layoutCounter+1].addView(blocks[ids+2]);
                groups[layoutCounter+1].addView(blocks[ids+3]);

                groups[layoutCounter].addView(groups[layoutCounter+1]);
                groups[layoutCounter].addView(blocks[ids+0]);
                groups[layoutCounter].addView(blocks[ids+1]);


                ids += 4;
                layoutCounter += 2;
                break;

            case 3://Bloque Raiz
                RelativeLayout.LayoutParams params8 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params8.addRule(RelativeLayout.RIGHT_OF, ids+3);
                params8.addRule(RelativeLayout.BELOW, ids+3);

                RelativeLayout.LayoutParams params9 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params9.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params9.addRule(RelativeLayout.ALIGN_PARENT_TOP);


                lista = new ArrayList<EditText>(Arrays.asList(blocks));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                lista.add(new EditText(c));
                blocks = lista.toArray(new EditText[lista.size()]);

                blocks[ids].setId(ids);
                blocks[ids+1].setId(ids+1);
                blocks[ids+2].setId(ids+2);
                blocks[ids+3].setId(ids+3);

                blocks[ids].setOnDragListener(dragListener);
                blocks[ids+1].setOnDragListener(dragListener);
                blocks[ids+2].setOnDragListener(dragListener);
                blocks[ids+3].setOnDragListener(dragListener);

                blocks[ids].setLayoutParams(params4);
                blocks[ids+1].setLayoutParams(params5);
                blocks[ids+2].setLayoutParams(params8);
                blocks[ids+3].setLayoutParams(params9);

                blocks[ids].setHint("(   )");
                blocks[ids+1].setHint("(   )");
                blocks[ids+2].setHint("(   )");
                blocks[ids+3].setHint("(   )");

                rl2.setLayoutParams(params3);



                MatematiBlock leftBlock2 = new MatematiBlock();
                leftBlock2.setText(ids);
                leftBlock2.setLayO(layoutCounter);
                elementos.add(leftBlock2);
                salida.addBlock(counter);
                counter = counter +1;

                MatematiBlock rightBlock2 = new MatematiBlock();
                rightBlock2.setText(ids+1);
                rightBlock2.setLayO(layoutCounter);
                elementos.add(rightBlock2);
                salida.addBlock(counter);
                counter = counter +1;


                MatematiBlock base2 = new MatematiBlock();
                base2.setText(ids+2);
                base2.setLayO(layoutCounter+1);
                elementos.add(base2);
                salida.addBlock(counter);
                counter = counter +1;

                MatematiBlock exponente2 = new MatematiBlock();
                exponente2.setText(ids+3);
                exponente2.setLayO(layoutCounter+1);
                elementos.add(exponente2);
                salida.addBlock(counter);
                counter = counter +1;

                salida.setStructure(" _0 ((_2 )^((1)/(_3 ))) _1 ");
                salida.counter = 4;
                salida.sub = true;



                ArrayList<RelativeLayout> grupos3 = new ArrayList<RelativeLayout>(Arrays.asList(groups));
                grupos3.add(new RelativeLayout(c));
                grupos3.add(new RelativeLayout(c));
                groups = grupos3.toArray(new RelativeLayout[grupos3.size()]);

                rl1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                groups[layoutCounter] = rl1;
                groups[layoutCounter+1] = rl2;

                groups[layoutCounter+1].addView(blocks[ids+2]);
                groups[layoutCounter+1].addView(blocks[ids+3]);

                groups[layoutCounter].addView(groups[layoutCounter+1]);
                groups[layoutCounter].addView(blocks[ids+0]);
                groups[layoutCounter].addView(blocks[ids+1]);


                ids += 4;
                layoutCounter += 2;
                break;
        }


        return salida;
    }

    public String getText(int obj){
        int i, j;

        if(elementos.get(obj).sub==false){
            return blocks[elementos.get(obj).text].getText().toString();
        }else{

            ArrayList<Integer> structValues = new ArrayList<>();

            //System.out.println("Structure: "+elementos.get(obj).structure);//control
            String firstList[] = elementos.get(obj).structure.split("_");
            String buffer = firstList[0];

            for(i=1;i<firstList.length;i++){
                String secondList[] = firstList[i].split(" ");
                int num = Integer.parseInt(secondList[0]);
                structValues.add(num);

                System.out.println("Nuevo obj: " + obj);//control
                System.out.println("Nuevo num: "+ num);//control

                System.out.println("Nuevo tot: "+elementos.get(obj).bloques[num]);//control
                String texto = getText(elementos.get(obj).bloques[num]);
                System.out.println(texto);

                buffer = buffer + texto;

                for(j=1; j<secondList.length;j++){
                    buffer = buffer + secondList[j];
                }

            }



            return buffer;
        }
    }

    public void add(int d){
        int u = counter;
        MatematiBlock buffer = new MatematiBlock();
        //counter = counter +1;
        elementos.add(buffer);
        elementos.set(u, creator(d));
    }

  public int getIblock(int id){
        int y = -1;

        for(int i=0; i<elementos.size(); i++){
            if(elementos.get(i).text==id){
                y=i;
            }
        }

        return y;
  }

    public int getIET(int id){
        int y = -1;

        for(int i=0; i<blocks.length; i++){
            if(blocks[i].getId()==id){
                y=i;
            }
        }

        return y;
    }

}
