package cl.telematica.android.certamen2.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen2.Frase;
import cl.telematica.android.certamen2.HttpServerConnection;
import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.value;

public class InputFragment extends Fragment {

    private EditText Nombre;
    private EditText Apellido;
    private Button Btnservidor;
    private TextView Txtparseado;
    /**
     * New instance of InputFragment
     *
     * @return new instance of InputFragment
     */
    public static InputFragment newInstance() {
        InputFragment fragment = new InputFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_input, null);

        //VEr si es con mainView o container
        Nombre = (EditText) mainView.findViewById(R.id.nombre);
        Apellido = (EditText)mainView.findViewById(R.id.apellido);
        Btnservidor = (Button)mainView.findViewById(R.id.btnservidor);
        Txtparseado = (TextView)mainView.findViewById(R.id.txtparseado);

        Btnservidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Si no funciona ver como ponerlos al comienzo de la definicion de la clase
                final String nombreet = Nombre.getText().toString();
                final String apellidoet = Apellido.getText().toString();

                //Tarea asincrónica
                AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... params) {
                        String resultado = new HttpServerConnection().connectToServer("http://api.icndb.com/jokes/random?firstName="+nombreet+"&lastName="+apellidoet, 15000);
                        return resultado;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        if (result != null) {
                            //System.out.println(result);
                            //parsear resultado y mostrarlo en pantalla

                            Txtparseado.setText(getLista(result));

                            // specify an adapter (see also next example)

                        }
                    }
                };

                task.execute();
            }
        });


        /*
         * Aquí es donde deben hacer el link a los elementos del layout fragment_input,
         * y donde prácticamente se debe hacer el desarrollo
        */

        return mainView;
    }

    //Quizás esté mal ubicado este método
    //Parsear el JSON
    private String getLista(String result){

        String frase = new String();

        try {

            JSONObject objeto = new JSONObject(result);
            JSONObject valuesArray = objeto.getJSONObject("value");


            frase= valuesArray.getString("joke");

            return frase;

        } catch (JSONException e1) {
            e1.printStackTrace();
            return frase;
        }



        }
    }

