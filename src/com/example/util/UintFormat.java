package com.example.util;

public class UintFormat {
	public static final int UINT8SIZE = 1;
	public static final int UINT16SIZE = 2;
	public static final int UINT32SIZE = 4;
	public static final int UINT64SIZE = 8;
	public static byte[] formatUINT8(int value){
		byte[] mValue = new byte[UINT8SIZE];
		mValue[0] = (byte)(value & 0xFF);
        return mValue;
	}
	public static int deformatUINT8(byte value){
		 return   value & 0xFF ;
	}	
	public static byte[] formatUINT16(int value){
		byte[] mValue = new byte[UINT16SIZE];
		mValue[0] = (byte)(value & 0xFF);
        mValue[1] = (byte)((value >> 8) & 0xFF);
        return mValue;
	}
	public static int deformatUINT16(byte[] value){
		 return   value[0] & 0xFF |
		            (value[1] & 0xFF) << 8 ;
	}	
	public static byte[] formatUINT32(int value){
		byte[] mValue = new byte[UINT32SIZE];
		mValue[0] = (byte)(value & 0xFF);
        mValue[1] = (byte)((value >> 8) & 0xFF);
        mValue[2] = (byte)((value >> 16) & 0xFF);
        mValue[3] = (byte)((value >> 24) & 0xFF);
        return mValue;
	}
	public static int deformatUINT32(byte[] value){
		 return   value[0] & 0xFF |
		            (value[1] & 0xFF) << 8 |
		            (value[2] & 0xFF) << 16 |
		            (value[3] & 0xFF) << 24;
	}	
	public static byte[] formatUINT64(long value){
		byte[] mValue = new byte[UINT64SIZE];
		mValue[0] = (byte)(value & 0xFF);
        mValue[1] = (byte)((value >> 8) & 0xFF);
        mValue[2] = (byte)((value >> 16) & 0xFF);
        mValue[3] = (byte)((value >> 24) & 0xFF);
		mValue[4] = (byte)((value >> 32 & 0xFF));
        mValue[5] = (byte)((value >> 40) & 0xFF);
        mValue[6] = (byte)((value >> 48) & 0xFF);
        mValue[7] = (byte)((value >> 56) & 0xFF);
        return mValue;
	}
	public static long deformatUINT64(byte[] value){
		 return   value[0] & 0xFF |
		            (value[1] & 0xFF) << 8 |
		            (value[2] & 0xFF) << 16 |
		            (value[3] & 0xFF) << 24|
		            (value[4] & 0xFF) << 32 |
		            (value[5] & 0xFF) << 40 |
		            (value[6] & 0xFF) << 48 |
		            (value[7] & 0xFF) << 56;
	}
}
