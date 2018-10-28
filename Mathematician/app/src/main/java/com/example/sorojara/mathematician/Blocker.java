package com.example.sorojara.mathematician;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
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

                        if(view.getId()==2000) {
                            int mat = getIblock(v.getId());
                            int layOut = elementos.get(mat).layO;
                            int ET = getIET(v.getId());

                            elementos.set(mat, creator(1));

                            System.out.println("LayoutCounter: " + layoutCounter);

                            groups[layoutCounter-2].setId(v.getId());

                            groups[layOut].removeView(blocks[ET]);
                            groups[layOut].addView(groups[layoutCounter-2]);






                        } else if(view.getId()==2002) {

                        } else if(view.getId()==2003) {

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
        RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params6.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params6.addRule(RelativeLayout.BELOW, 2001);

        RelativeLayout.LayoutParams params7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        params7.addRule(RelativeLayout.CENTER_IN_PARENT);

        blocks[0].setLayoutParams(params7);
        blocks[0].setOnDragListener(dragListener);

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

        //ARREGLAR DRAG LISTENER
        View.OnDragListener dl = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (v instanceof EditText) {
                    v.getId();

                }
                return  true;
            }
        };

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
                RelativeLayout rl1= new RelativeLayout(c);
                RelativeLayout rl2= new RelativeLayout(c);
                rl2.setId(layoutCounter + 420);

                //PARAMETROS DE LAYOUT:
                //dividendo
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                //divisor
                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params2.addRule(RelativeLayout.BELOW, ids);

                //fraccion
                RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params3.addRule(RelativeLayout.CENTER_IN_PARENT);

                //izquierda
                RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params4.addRule(RelativeLayout.CENTER_VERTICAL);
                params4.addRule(RelativeLayout.LEFT_OF, layoutCounter + 420);
                //derecha

                RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params5.addRule(RelativeLayout.CENTER_VERTICAL);
                params5.addRule(RelativeLayout.RIGHT_OF, layoutCounter + 420);



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

                blocks[ids].setOnDragListener(dl);
                blocks[ids+1].setOnDragListener(dl);
                blocks[ids+2].setOnDragListener(dl);
                blocks[ids+3].setOnDragListener(dl);

                blocks[ids].setLayoutParams(params4);
                blocks[ids+1].setLayoutParams(params5);
                blocks[ids+2].setLayoutParams(params);
                blocks[ids+3].setLayoutParams(params2);

                rl2.setLayoutParams(params3);




                MatematiBlock leftBlock = new MatematiBlock();
                leftBlock.setText(ids);
                leftBlock.setLayO(layoutCounter);

                elementos.add(leftBlock);
                counter = counter +1;
                salida.addBlock(counter);

                MatematiBlock rightBlock = new MatematiBlock();
                rightBlock.setText(ids+1);
                rightBlock.setLayO(layoutCounter);
                elementos.add(rightBlock);
                counter = counter +1;
                salida.addBlock(counter);

                MatematiBlock dividendo = new MatematiBlock();
                dividendo.setText(ids+2);
                dividendo.setLayO(layoutCounter+1);
                elementos.add(dividendo);
                counter = counter +1;
                salida.addBlock(counter);

                //SI DEJA DE SERVIR; DESCOMENTAR ESTO:
                // salida.addBlock(counter);

                MatematiBlock divisor = new MatematiBlock();
                divisor.setText(ids+3);
                divisor.setLayO(layoutCounter+1);
                elementos.add(divisor);
                counter = counter +1;
                salida.addBlock(counter);

                salida.setStructure(" _0 ((_2 )/(_3 )) _1 ");
                salida.counter = 4;
                salida.sub = true;

                ArrayList<RelativeLayout> grupos = new ArrayList<RelativeLayout>(Arrays.asList(groups));
                grupos.add(new RelativeLayout(c));
                grupos.add(new RelativeLayout(c));
                groups = grupos.toArray(new RelativeLayout[lista.size()]);


                rl1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT));
                groups[layoutCounter] = rl1;
                groups[layoutCounter+1] = rl2;

                groups[layoutCounter+1].addView(blocks[ids+2]);
                groups[layoutCounter+1].addView(blocks[ids+3]);

                groups[layoutCounter].addView(groups[layoutCounter+1]);
                groups[layoutCounter].addView(blocks[ids+0]);
                groups[layoutCounter].addView(blocks[ids+1]);





                ids += 4;
                layoutCounter += + 2;
                break;

            case 2://Bloque Exponencial
                MatematiBlock leftBlock1 = new MatematiBlock();
                elementos.add(leftBlock1);
                counter = counter +1;
                salida.addBlock(counter);
                MatematiBlock rightBlock1 = new MatematiBlock();
                elementos.add(rightBlock1);
                counter = counter +1;
                salida.addBlock(counter);
                salida.sub = true;

                MatematiBlock base = new MatematiBlock();
                elementos.add(base);
                counter = counter +1;
                salida.addBlock(counter);
                MatematiBlock exponente = new MatematiBlock();
                elementos.add(exponente);
                counter = counter +1;
                salida.addBlock(counter);

                salida.setStructure(" _0 ((_2 )^(_3 )) _1 ");
                salida.counter = 4;
                break;

            case 3://Bloque Raiz
                MatematiBlock leftBlock2 = new MatematiBlock();
                elementos.add(leftBlock2);
                counter = counter +1;
                salida.addBlock(counter);
                MatematiBlock rightBlock2 = new MatematiBlock();
                elementos.add(rightBlock2);
                counter = counter +1;
                salida.addBlock(counter);
                salida.sub = true;

                MatematiBlock base2 = new MatematiBlock();
                elementos.add(base2);
                counter = counter +1;
                salida.addBlock(counter);
                MatematiBlock exponente2 = new MatematiBlock();
                elementos.add(exponente2);
                counter = counter +1;
                salida.addBlock(counter);

                salida.setStructure(" _0 ((_2 )^(1/(_3 )) _1 ");
                salida.counter = 4;
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
