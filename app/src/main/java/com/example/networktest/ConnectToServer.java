package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
    public BufferedReader br;
    public TextView response;
    public String serverResponse;
    public boolean FirstTime= true;
    //public DataOutputStream dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_server);
        Button button = findViewById(R.id.quit);
        Button button2 = findViewById(R.id.Connect);
        Button button3 = findViewById(R.id.Ping);
        response = findViewById(R.id.response);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            isQuitting = true;
            //Quit();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect();
                if(FirstTime)
                {
                    waitForResponse();
                    FirstTime = false;
                }
                //response.setText(serverResponse);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ping();

                //Quit();
            }
        });
    }


    private void Quit(){
        QuitRunnable runnable = new QuitRunnable();
        new Thread(runnable).start();
    }

    private void Connect(){
        ConnectToServerRunnable runnable = new ConnectToServerRunnable();
        Thread tr = new Thread(runnable);
        tr.start();
        try {
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void Ping(){


        PingRunnable runnable = new PingRunnable();
        new Thread(runnable).start();
    }

    private void waitForResponse(){


        WaitForResponseRunnable runnable = new WaitForResponseRunnable();
        Thread tr= new Thread(runnable);
        tr.start();




    }




    class ConnectToServerRunnable implements  Runnable{

        @Override
        public void run() {

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);


                //serverResponse = br.readLine();


                //String str = br.readLine();


            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    class PingRunnable implements  Runnable{

        @Override
        public void run() {
                out.write("Ping\n");

                out.flush();
                //out.flush();
                //out.close();

        }
    }

    class WaitForResponseRunnable implements  Runnable{

        @Override
        public void run() {



                while(!isQuitting) {
                    try {
                        serverResponse = br.readLine();
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                response.setText(serverResponse);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

           /* try {



                System.out.println(socket.getLocalPort());

                //serverResponse = br.readLine();

            }catch(IOException e){
                e.printStackTrace();
            }*/
        }
    }

    class QuitRunnable implements  Runnable{

        @Override
        public void run() {

            try {
                //br.close();
                socket.close();

            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }

    /*private class ConnectToServerAsyncTask extends AsyncTask<Void, Void, Void> {

        String serverResponse;


        @Override
        protected Void doInBackground(Void... voids) {
            //InputStream inputStream = null;


            try {

                socket = new Socket(SERVER_IP, SERVER_PORT);
                out = new PrintWriter(socket.getOutputStream(),true);
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                //System.out.println("> ");
                //String command = keyboard.readLine();

                //if(command.equals("quit")) break;



                // serverResponse = br.readLine();




                //out.println(command);
                //out.flush();
                //serverResponse = input.readLine();
                //System.out.println("Server says: " + serverResponse);
                    /*if( !serverResponse.isEmpty())
                    {
                        System.out.println("Server says: " + serverResponse);
                    }
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
            //response.setText(serverResponse);


        }


    }


    private class WaitForResponseAsyncTask extends AsyncTask<Void, Void, Void> {

        String serverResponse;

        @Override
        protected Void doInBackground(Void... voids) {

            try {



                serverResponse = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }





            return null;
        }





        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            response.setText(serverResponse);

        }


    }

    /*void DisplayText(String message){
        DisplayMessageAsyncTask asyncTask = new DisplayMessageAsyncTask(message);
        asyncTask.execute();


    }


    private class PingAsyncTask extends AsyncTask<Void, Void, Void> {

        String serverResponse;


        @Override
        protected Void doInBackground(Void... voids) {
            //InputStream inputStream = null;
            out.write("Ping");
            //out.flush();
            out.flush();
            //out.close();
            /*try {



                serverResponse = br.readLine();
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

    /*private class DisplayMessageAsyncTask extends AsyncTask<Void, Void, Void> {
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

*/
}
