package com.mingdi101.annotations.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * File copy NIO VS BIO
 * 
 * use buffer size as 1000: ####time elapse with nio = 273 !!!!time elapse with
 * bio = 392
 * 
 * use buffer size as 8192. default memory process unit. seem BIO more related
 * to buffer size
 *  ####time elapse with nio = 274 
 *  !!!!time elapse with bio = 340
 * 
 * @author Administrator
 *
 */
public class FileCopyBio {

	public static void main(String[] args) throws IOException {

		File dir = new File("C:\\Users\\Administrator\\Downloads");
		File[] fs;
		if (dir.isDirectory()) {
			fs = dir.listFiles();
			LocalTime start = LocalTime.now();
			// nio read start
			Arrays.stream(fs).forEach(f -> {
				if (f.isDirectory()) {
					return;
				}
				try {
					ByteBuffer buffer = ByteBuffer.allocate(8192);
					FileInputStream fis = new FileInputStream(f);
					FileChannel sourceChannel = fis.getChannel();
					FileOutputStream fos = new FileOutputStream("F:/temp/" + f.getName());
					FileChannel targetChannel = fos.getChannel();
					while (sourceChannel.read(buffer) > 0) {
						sourceChannel.transferTo(targetChannel.position(), buffer.capacity(), targetChannel);
						buffer.flip();
					}

					sourceChannel.close();
					targetChannel.close();
					fis.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			System.out
					.println("####time elapse with nio = " + (LocalTime.now().toSecondOfDay() - start.toSecondOfDay()));

			// bio read start
			start = LocalTime.now();
			Arrays.stream(fs).forEach(f -> {
				if (f.isDirectory()) {
					return;
				}
				try {
					FileInputStream fis = new FileInputStream(f);
					byte[] b = new byte[8192];
					FileOutputStream fos = new FileOutputStream("F:/temp2/" + f.getName());
					while (fis.read(b) > 0) {
						fos.write(b);
					}
					fis.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			System.out
					.println("!!!!time elapse with bio = " + (LocalTime.now().toSecondOfDay() - start.toSecondOfDay()));
		}

		// LocalTime start = LocalTime.now();
		// FileInputStream fis = new FileInputStream("F:/hildon_travel.doc");
		//
		// byte[] b = new byte[100];
		// int data = fis.read(b);
		// StringBuffer sb = new StringBuffer();
		// while(data>0) {
		// sb.append(sb);
		// data = fis.read(b);
		// }
		// fis.close();
		// File outfile = new File("F:/hildon_travel_bio_output.doc");
		// if(outfile.exists()) {
		// outfile.delete();
		// }
		// outfile.createNewFile();
		// FileOutputStream fos = new FileOutputStream(outfile);
		// b = new byte[100];
		// fos.write(sb.toString().getBytes());
		// fos.close();
		// System.out.println("time elapse = " + LocalTime.now().compareTo(start));
	}
}
