package com.mingdi101.annotations.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileCopy {

	public static void main(String[] args) throws IOException {
		File source = new File("C:\\Users\\Administrator\\Downloads\\51ym.apk");
		File target = new File("F:/target.apk");
		if(target.exists()) {
			target.delete();
		} else {
			target.createNewFile();
		}
		FileChannel fcs = new FileInputStream(source).getChannel();
		FileChannel fct = new FileOutputStream(target).getChannel();
		ByteBuffer bf = ByteBuffer.allocate(1000);
		while(fcs.read(bf)>0) {
			fcs.transferTo(fct.position(), bf.capacity(), fct);
			bf.flip();
		}
		fct.close();
		fcs.close();
		
	}
}
