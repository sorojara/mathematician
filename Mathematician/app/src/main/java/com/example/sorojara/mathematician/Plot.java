package com.example.sorojara.mathematician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.softmoore.android.graphlib.Function;
import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;



public class Plot extends AppCompatActivity {

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
        Bundle extras = getIntent().getExtras();
        value = extras.getString("key");


        com.softmoore.android.graphlib.Function graphing = new Function() {
            @Override
            public double apply(double x)
            {
                org.mariuszgromada.math.mxparser.Function  f = new org.mariuszgromada.math.mxparser.Function("f", value, "x");
                return f.calculate(x);
            }
        };


        Graph graph = new Graph.Builder()
                .addFunction(graphing)
                .setWorldCoordinates(-10, 10, -10, 10)
                .build();
        GraphView graphView = findViewById(R.id.graph_view);
        graphView.setGraph(graph);
        setTitle("Function by user: ");
        TextView textView = findViewById(R.id.graph_view_label);
        textView.setText(value);


    }

}
