package com.examlpe.util.hardware;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import com.example.util.LOG;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.util.SparseArray;
import android.widget.Toast;
/**
 * 綁定生命周期方法(千萬別忘了onNewIntent()方法，該方法也必須在activity的onNewIntent()方法中調用)
 * @author 
 *		<uses-permission android:name="android.permission.NFC"/>
 */
public class NFCUtil {
	private NfcAdapter adapter;
	private PendingIntent mPendingIntent;
	private Activity activity;
	private Tag tag;
	private LOG log;
	public NFCUtil(Activity activity){
		log = LOG.zLog("");
		this.activity = activity;
		adapter = NfcAdapter.getDefaultAdapter(activity);
		mPendingIntent = PendingIntent.getActivity(activity, 0, new Intent(activity,
				activity.getClass()), 0);
	}
	public void onResume() {
		if (adapter != null)
			adapter.enableForegroundDispatch(activity, mPendingIntent, null,
					null);
	}

	public void onPause() {
		if (adapter != null)
			adapter.disableForegroundDispatch(activity);
	}
	/**
	 * 此方法必須在activity的onNewIntent()中調用，否者無法使用NFC
	 * @param intent  activity中該方法的參數
	 */
	public void onNewIntent(Intent intent) {
		tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
	}
	/**
	 * 創建自動啟動APP的TAG   寫入NFC貼紙，當NFC貼紙靠近手機時會自動啟動包名對應的程序
	 * 	要求手機系統4.0以上
	 * @param mPackageName  寫入的包名
	 */
	public void createLaunchAppNFCTag(String mPackageName) {
		if (tag == null) {
			return;
		}
		NdefMessage ndefMessage = new NdefMessage(
				new NdefRecord[] { NdefRecord
						.createApplicationRecord(mPackageName)});
		int size = ndefMessage.toByteArray().length;
		try {
			Ndef ndef = Ndef.get(tag);
			if(ndef!=null){
				ndef.connect();
				if(!ndef.isWritable()){
					return;
				}
				if(ndef.getMaxSize() < size){
					return;
				}
				ndef.writeNdefMessage(ndefMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 當NFC Tag靠近時手機會自動打開某個網頁
	 * @param url  網頁地址
	 */
	public void createWebNFCTag(String url){
		if (tag == null) {
			return;
		}
		NdefMessage ndefMessage = new NdefMessage(
				new NdefRecord[] { NdefRecord.createUri(Uri
						.parse(url)) });
		int size = ndefMessage.toByteArray().length;
		try{
			Ndef ndef = Ndef.get(tag);
			if(ndef != null){
				ndef.connect();
				if(!ndef.isWritable()){
					return;
				}
				if(ndef.getMaxSize() < size){
					return;
				}
				ndef.writeNdefMessage(ndefMessage);
			}else {
				//格式化NFC
				NdefFormatable format = NdefFormatable.get(tag);
				if(format != null){
					format.connect();
					format.format(ndefMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用text創建一條NDEF數據
	 * @param text 構造NDEF數據的text字符串
	 * @return 代表NDEF數據的對象
	 */
	public NdefMessage createTextRecord(String text) {
		byte[] langBytes = Locale.CHINA.getLanguage().getBytes(
				Charset.forName("US-ASCII"));
		Charset utfEncoding = Charset.forName("UTF-8");
		byte[] textBytes = text.getBytes(utfEncoding);
		int utfBit = 0;
		char status = (char) (utfBit + langBytes.length);
		byte[] data = new byte[1 + langBytes.length + textBytes.length];
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
				textBytes.length);
		NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
				NdefRecord.RTD_TEXT, new byte[0], data);
		NdefMessage ndefMessage = new NdefMessage(
				new NdefRecord[] { ndefRecord });
		return ndefMessage;
	}
	/**
	 * 將NDEF格式資料寫入NFC設備
	 * @param message
	 */
	public void writeNDEFMessage(NdefMessage message){
		Ndef ndef = Ndef.get(tag);
		try {
			ndef.connect();
			ndef.writeNdefMessage(message);
		} catch (IOException e) {
			log.i("NFC連接失敗...");
			e.printStackTrace();
		} catch (FormatException e) {
			log.i("NDEF格式錯誤...");
			e.printStackTrace();
		}
	}
	/**
	 * 讀取NDEF格式標籤
	 * @return
	 */
	public NFCTextRecord readNFCTag() {
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(activity.getIntent().getAction())) {
			Parcelable[] rawMsgs = activity.getIntent().getParcelableArrayExtra(
					NfcAdapter.EXTRA_NDEF_MESSAGES);
			NdefMessage msgs[] = null;
			if (rawMsgs != null) {
				msgs = new NdefMessage[rawMsgs.length];
				for (int i = 0; i < rawMsgs.length; i++) {
					msgs[i] = (NdefMessage) rawMsgs[i];
				}
			}
			try {
				if (msgs != null) {
					NdefRecord record = msgs[0].getRecords()[0];
					NFCTextRecord textRecord = NFCTextRecord.parse(record);
					return textRecord;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 寫入MifareUltralight標籤
	 * @param arr
	 * @throws Exception
	 */
	public void writeMifareUltralight(SparseArray arr) throws Exception{
		String[] techList =tag.getTechList();
		boolean haveMifareUltralight = false;
    	for(String tech: techList){
    		if(tech.indexOf("MifareUltralight") >= 0){
    			haveMifareUltralight = true;
    			break;
    		}
    	}
    	if(!haveMifareUltralight){
    		Toast.makeText(activity,"不支援MifareUltralight格式", Toast.LENGTH_LONG).show();
    		return;
    	}
		MifareUltralight mif = MifareUltralight.get(tag);
		mif.connect();
		for(int i = 0 ; i < arr.size() ; i++){
			mif.writePage(i, arr.get(i).toString().getBytes());
		}
		Toast.makeText(activity, "成功寫入MifareUltralight格式!", Toast.LENGTH_LONG).show();
		mif.close();
	}
	/**
	 * 讀取MifareUltralight標籤
	 * @return
	 */
	public String readMifareUltralight(){
		MifareUltralight ultralight = MifareUltralight.get(tag);
		try{
			ultralight.connect();
			byte[] data = ultralight.readPages(4);
			return new String(data, Charset.forName("GB2312"));
		}catch (Exception e) {
			log.i("MifareUltralight讀取異常");
			e.printStackTrace();
		}finally{
			try{
				ultralight.close();
			}catch (Exception e) {
				log.i("MifareUltralight關閉異常");
				e.printStackTrace();
			}
		}
		return null;
	}
}
