package com.appstudio.sample.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Locale;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.nfc.NdefRecord;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.appstudio.sample.android.c21_wifidirect.WiFiDirectActivity;

public class Dummy {

  public NdefRecord createTextRecord(String payload, 
                         Locale locale, boolean encodeInUtf8) {
  
    byte[] langBytes = locale.getLanguage().getBytes(
                                  Charset.forName("US-ASCII"));
  
    Charset utfEncoding = encodeInUtf8 ? 
          Charset.forName("UTF-8") : Charset.forName("UTF-16");
          
    byte[] textBytes = payload.getBytes(utfEncoding);
    int utfBit = encodeInUtf8 ? 0 : (1 << 7);
    char status = (char) (utfBit + langBytes.length);
    byte[] data = new byte[1 + langBytes.length + 
                                            textBytes.length];
    data[0] = (byte) status;
    System.arraycopy(langBytes, 0, data, 1, langBytes.length);
    System.arraycopy(textBytes, 0, data, 1 + 
                          langBytes.length, textBytes.length);
    NdefRecord record = new NdefRecord(
                                    NdefRecord.TNF_WELL_KNOWN,
    NdefRecord.RTD_TEXT, new byte[0], data);
    return record;
  }
    
void tmp1()  {

  byte[] uriField = "example.com".getBytes(
                                Charset.forName("US-ASCII"));
  byte[] payload = new byte[uriField.length + 1];
  payload[0] = 0x01;                        
  System.arraycopy(uriField, 0, payload, 1, uriField.length);
  NdefRecord rtdUriRecord = new NdefRecord(
              NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_URI, 
                                       new byte[0], payload);
  
}

void tmp2()  {
  byte[] payload=null;
  // 필요 로직 기술
  byte[] externalType = "example.com:externalType".
                      getBytes(Charset.forName("US-ASCII"));
  NdefRecord mimeRecord = new NdefRecord(
                 NdefRecord.TNF_EXTERNAL_TYPE, externalType,
                                      new byte[0], payload);
}


void tmp3()  {
  WifiP2pManager manager=null;
  boolean isWifiP2pEnabled = false;
  boolean retryChannel = false;
  final IntentFilter intentFilter = new IntentFilter();
  Channel channel=null;
  
  manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
    @Override
    public void onSuccess() {
      // 성공 시 로직 
    }

    @Override
    public void onFailure(int reasonCode) {
      // 실패 시 로직 
    }
  });
}

void temp3()  {
WifiP2pManager manager=null; 
Channel channel=null;
WifiP2pDevice device=null;

//obtain a peer from the WifiP2pDeviceList
WifiP2pConfig config = new WifiP2pConfig();
config.deviceAddress = device.deviceAddress;
manager.connect(channel, config, new ActionListener() {

  @Override
  public void onSuccess() {
      //success logic
  }

  @Override
  public void onFailure(int reason) {
      //failure logic
  }
});
}

public static class FileServerAsyncTask extends AsyncTask {

  private Context context;
  private TextView statusText;

  public FileServerAsyncTask(Context context, View statusText) {
      this.context = context;
      this.statusText = (TextView) statusText;
  }

  @Override
  protected Object doInBackground(Object... params) {
    try {
      // 서버 소켓을 생성 후 클라이언트가 연결 할 때까지 대기
      ServerSocket serverSocket = new ServerSocket(8888);
      Socket client = serverSocket.accept();

      final File f = new File(Environment.
                getExternalStorageDirectory() + "/"+ 
                context.getPackageName() + 
                "/wifip2pshared-" + System.currentTimeMillis()+
                ".jpg");

      File dirs = new File(f.getParent());
      if (!dirs.exists())
        dirs.mkdirs();
      f.createNewFile();
      InputStream inputstream = client.getInputStream();
      copyFile(inputstream, new FileOutputStream(f));
      serverSocket.close();
      return f.getAbsolutePath();
      } catch (IOException e) {
        Log.e(WiFiDirectActivity.TAG, e.getMessage());
        return null;
      }
  }

  /**
   * JPEG 파일을 처리하는 액티비티를 기
   */
  @Override
  protected void onPostExecute(Object result) {
    if (result != null) {
      statusText.setText("File copied - " + result);
      Intent intent = new Intent();
      intent.setAction(android.content.Intent.ACTION_VIEW);
      intent.setDataAndType(
                     Uri.parse("file://" + result), "image/*");
      context.startActivity(intent);
    }
  }

  
  


}



public static void copyFile(InputStream inputstream, FileOutputStream fileOutputStream) {
  // TODO Auto-generated method stub
  
}

void temp4()  {
//  Context context = this.getApplicationContext();
  String host;
  int port;
  int len;
  Socket socket = new Socket();
  byte buf[]  = new byte[1024];
  // ...
  try {
    /**
     * 서버 주소와 포트로 클라이언트 소켓을 생성 
     * 타임아웃 지정 
     */
    socket.bind(null);
//    socket.connect((new InetSocketAddress(host, port)), 500);

    /**
     * 파일에서 읽어서 소켓의 출력스트림으로 write한다.
     */
    OutputStream outputStream = socket.getOutputStream();
    ContentResolver cr = null; //context.getContentResolver();
    InputStream inputStream = null;
    inputStream = cr.openInputStream(Uri.parse("path/to/picture.jpg"));
    while ((len = inputStream.read(buf)) != -1) {
      outputStream.write(buf, 0, len);
    }
    outputStream.close();
    inputStream.close();
  } catch (FileNotFoundException e) {
    //catch logic
  } catch (IOException e) {
    //catch logic
  } finally {
    if (socket != null) {
      if (socket.isConnected()) {
        try {
          socket.close();
        } catch (IOException e) {
          //catch logic
        }
      }
    }
  }  
}

}




