package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.util.Log;


public class ByteTool {
	
	public static byte[] appendByteArray(byte[] a , byte[] b){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		try {
			outputStream.write( a );
			outputStream.write( b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray( );
	}

	public static String getHexStringBybyteArray(byte[] b){
   	  final StringBuilder stringBuilder = new StringBuilder(b.length);
         for(byte byteChar : b)
             stringBuilder.append(String.format("%02X ", byteChar));
		Log.d("DEYU" , "setCharacteristicValue :" + stringBuilder.toString());
		return stringBuilder.toString();
	}
	public static byte[] getBytesByIndexAndLength(byte[] data , int index , int length){
		Log.d("DEYU-BYTE", "getBytesByIndexAndLength : data lenght : " + data.length + " index : " + index + " lenght : + " + length);
		int PacketTypeIndex =index;
		byte[] returnBytes =  new byte[length];
		for(int i = 0 ; i < returnBytes.length ; i ++ , PacketTypeIndex ++){
			if(PacketTypeIndex<data.length)returnBytes[i] = data[PacketTypeIndex];
		}
		return returnBytes;
	}
	public static int covertBytesToInt(byte[] data){
		int returnint = 0;
		switch (data.length) {
		case UintFormat.UINT16SIZE:
			returnint = UintFormat.deformatUINT16(data);
			break;
		case UintFormat.UINT32SIZE:
			returnint = UintFormat.deformatUINT32(data);
			break;
		}
		return returnint;
	}
	public static long covertBytesToLong(byte[] data){
		long returnint = 0;
		switch (data.length) {
		case UintFormat.UINT64SIZE:
			returnint = UintFormat.deformatUINT64(data);
			break;
		}
		return returnint;
	}
	public static void put(byte[] data , byte[] insert , int offset , int lenght){
		for(int i = 0 ; i < lenght ; i++){
			data[offset + i] = insert[i];
		}
	}

}
