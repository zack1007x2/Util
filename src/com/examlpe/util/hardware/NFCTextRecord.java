package com.examlpe.util.hardware;

import java.util.Arrays;

import android.nfc.NdefRecord;
/**
 * 內部接口（用於取得NFC設備里的文本數據）
 * @author lee
 *
 */
public class NFCTextRecord {
	private final String mText;
	private NFCTextRecord(String text) {
		mText = text;
	}
	/**
	 * 取得文本數據
	 * @return
	 */
	public String getText() {
		return mText;
	}
	/**
	 * 解析NDEF數據
	 * @param ndefRecord
	 * @return
	 */
	public static NFCTextRecord parse(NdefRecord ndefRecord) {
		if (ndefRecord.getTnf() != NdefRecord.TNF_WELL_KNOWN) {
			return null;
		}
		if (!Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
			return null;
		}
		try {
			byte[] payload = ndefRecord.getPayload();
			String textEncoding = ((payload[0] & 0x80) == 0) ? "UTF-8"
					: "UTF-16";
			int languageCodeLength = payload[0] & 0x3f;
			String text = new String(payload, languageCodeLength + 1,
					payload.length - languageCodeLength - 1, textEncoding);
			return new NFCTextRecord(text);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

}
