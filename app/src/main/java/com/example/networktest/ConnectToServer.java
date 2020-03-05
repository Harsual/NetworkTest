package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectToServer extends AppCompatActivity {
    private static final String SERVER_IP = "192.168.1.37";
    private static final int SERVER_PORT = 9090;
    public static boolean isQuitting = false;
    //public AsyncTask asyncTask;
    TextView txt;
    public Socket socket;
    public PrintWriter out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_server);
        Button button = findViewById(R.id.quit);
        Button button2 = findViewById(R.id.Connect);
        Button button3 = findViewById(R.id.Ping);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            isQuitting = true;
            Quit();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ping();

            }
        });
    }


    private void Quit(){
        QuitAsyncTask asyncTask = new QuitAsyncTask();
        asyncTask.execute();
    }

    private void Connect(){
        ConnectToServerAsyncTask asyncTask = new ConnectToServerAsyncTask();
        asyncTask.execute();
    }

    private void Ping(){


        PingAsyncTask asyncTask = new PingAsyncTask();
        asyncTask.execute();
    }


    private class ConnectToServerAsyncTask extends AsyncTask<Void, Void, Void> {




        @Override
        protected Void doInBackground(Void... voids) {
            //InputStream inputStream = null;

            String serverResponse;
            try {

                socket = new Socket(SERVER_IP, SERVER_PORT);


                    //out = new PrintWriter(socket.getOutputStream());


                    //System.out.println("> ");
                    //String command = keyboard.readLine();

                    //if(command.equals("quit")) break;


                    //out.println(command);
                    //out.flush();
                    //serverResponse = input.readLine();
                    //System.out.println("Server says: " + serverResponse);
                    /*if( !serverResponse.isEmpty())
                    {
                        System.out.println("Server says: " + serverResponse);
                    }*/
                    //socket.close();
                    //System.exit(0);





                //socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }



            //adapter.notifyDataSetChanged();
            // stop animating Shimmer and hide the layout
            // mShimmerViewContainer.stopShimmer();
            //mShimmerViewContainer.setVisibility(View.GONE);


            return null;
        }





        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }


    }

    void DisplayText(String message){
        DisplayMessageAsyncTask asyncTask = new DisplayMessageAsyncTask(message);
        asyncTask.execute();


    }


    private class PingAsyncTask extends AsyncTask<Void, Void, Void> {




        @Override
        protected Void doInBackground(Void... voids) {
            //InputStream inputStream = null;

            String serverResponse;
            try {

                //Socket socket = new Socket(SERVER_IP, SERVER_PORT);

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                //PrintWriter out = new PrintWriter(socket.getOutputStream());
                out = new PrintWriter(socket.getOutputStream());
                out.write("Ping");
                out.flush();
                out.close();



                //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while(true) {

                    serverResponse = input.readLine();
                    boolean atleastOneAlpha = serverResponse.matches(".*[a-zA-Z]+.*");
                    //System.out.println("Server says: " + serverResponse);
                    if (isQuitting) break;

                    if (atleastOneAlpha) {

                        System.out.println("Server says: " + serverResponse);
                    }
                    //System.out.println("> ");
                    //String command = keyboard.readLine();

                    //if(command.equals("quit")) break;


                    //out.println(command);
                    //out.flush();

                }

                input.close();


            } catch (IOException e) {
                e.printStackTrace();
            }



            //adapter.notifyDataSetChanged();
            // stop animating Shimmer and hide the layout
            // mShimmerViewContainer.stopShimmer();
            //mShimmerViewContainer.setVisibility(View.GONE);


            return null;
        }





        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }


    }

    private class DisplayMessageAsyncTask extends AsyncTask<Void, Void, Void> {
        String message;


        public DisplayMessageAsyncTask(String message){
            this.message = message;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            txt.setText(message);
            return null;
        }





        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(ConnectToServer.this,"Pinged!!",Toast.LENGTH_LONG).show();

        }


    }

    private class QuitAsyncTask extends AsyncTask<Void, Void, Void> {





        @Override
        protected Void doInBackground(Void... voids) {
            try {


                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }





        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }


    }


}
